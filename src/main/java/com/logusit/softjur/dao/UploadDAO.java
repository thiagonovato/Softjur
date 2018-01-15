package com.logusit.softjur.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.logusit.softjur.bean.AudienciaBean;
import com.logusit.softjur.domain.Audiencia;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Correspondente;
import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.domain.ErrosExcel;
import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.domain.Estado;
import com.logusit.softjur.domain.Excel;
import com.logusit.softjur.domain.ExcelDuplicado;
import com.logusit.softjur.domain.Orgao;
import com.logusit.softjur.domain.TipoAudiencia;
import com.logusit.softjur.domain.Upload;

import jxl.read.biff.BiffException;

public class UploadDAO extends GenericDAO<Upload> {

	ArrayList<ErrosExcel> errosExcel = new ArrayList<>();
	ArrayList<ExcelDuplicado> excelDuplicado = new ArrayList<>();

	private static final UploadDAO INSTANCE = new UploadDAO();

	ParametrosGeraisDAO pgDAO = new ParametrosGeraisDAO();
	String pathUpload = pgDAO.listarParametros().getPathUpload();

	private List<Audiencia> listaResultado;
	Long correspondenteLocalizado = 0L;
	Long orgaoLocalizado = 0L;
	Long empresaLocalizada = 0L;
	Long escritorioLocalizado = 0L;
	Long tipoAudienciaLocalizado = 0L;
	Long cidadeLocalizada = 0L;
	Long estadoLocalizado = 0L;

	String correspondenteLocalizadoNome = "";
	String orgaoLocalizadoNome = "";
	String empresaLocalizadaNome = "";
	String escritorioLocalizadoNome = "";
	String tipoAudienciaLocalizadoNome = "";
	String cidadeLocalizadaNome = "";
	String estadoLocalizadoNome = "";

	Boolean correspondenteOK = false;
	Boolean orgaoOK = false;
	Boolean empresaOK = false;
	Boolean escritorioOK = false;
	Boolean tipoAudienciaOK = false;
	Boolean cidadeOK = false;
	Boolean estadoOK = false;

	private Correspondente correspondente;
	private Orgao orgao;
	private TipoAudiencia tipoAudiencia;
	private Empresa empresa;
	private Escritorio escritorio;

	// private UploadDAO() {}

	public void write(Part part) throws IOException {
		Date data = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmm");
		String dataArquivo = dt.format(data);
		String fileName = dataArquivo + extractFileName(part);
		System.out.println("Arquivo: " + fileName);

		String filePath = pathUpload;

		File fileSaveDir = new File(filePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		part.write(filePath + File.separator + fileName);
	}

	public String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public static UploadDAO getInstance() {
		return INSTANCE;
	}

	public ArrayList<Excel> listarExcel(Upload upload) throws BiffException, IOException, ParseException {
		String arquivoExcel = upload.getNome();

		ArrayList<Excel> uploadsExcel = new ArrayList<>();

		try {

			// Converter a data do arquivo para o formato da pasta
			Date data = upload.getDataCadastro();
			SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
			String dataPasta = dt.format(data);

			String fileName = pathUpload + "/" + arquivoExcel;

			FileInputStream arquivo = new FileInputStream(new File(fileName));

			HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
			HSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Excel excel = new Excel();
				ErrosExcel erros = new ErrosExcel();
				ExcelDuplicado duplicados = new ExcelDuplicado();

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				Correspondente correspondente = new Correspondente();
				Orgao orgao = new Orgao();
				Cidade cidade = new Cidade();
				Estado estado = new Estado();
				Empresa empresa = new Empresa();
				TipoAudiencia tipoAudiencia = new TipoAudiencia();
				Escritorio escritorio = new Escritorio();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						// CORRESPONDENTE
						excel.setColuna1(cell.getStringCellValue());
						CorrespondenteDAO cdao = new CorrespondenteDAO();
						correspondente = cdao.buscarPorNome(excel.getColuna1());
						if (correspondente == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Correspondente não localizado: " + excel.getColuna1());
							errosExcel.add(erros);
							correspondenteLocalizado = 0L;
							correspondenteOK = false;
						} else {
							correspondenteLocalizado = correspondente.getCodigo();
							correspondenteLocalizadoNome = correspondente.getPessoa().getNome();
							correspondenteOK = true;
						}
						break;
					case 1:
						// DATA
						SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
						String dataString = formatoData.format(cell.getDateCellValue());
						excel.setColuna2(dataString);
						break;
					case 2:
						// HORA
						SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
						String horaString = formatoHora.format(cell.getDateCellValue());
						excel.setColuna3(horaString);
						break;
					case 3:
						// NÚMERO DO ÓRGÃO
						cell.setCellType(CellType.STRING);
						excel.setColuna4(cell.getStringCellValue());
						break;
					case 4:
						// NOME DO ÓRGÃO
						cell.setCellType(CellType.STRING);
						excel.setColuna5(cell.getStringCellValue());
						break;
					case 5:
						// CIDADE
						cell.setCellType(CellType.STRING);
						excel.setColuna6(cell.getStringCellValue());
						// VALIDANDO ÓRGÃO
						OrgaoDAO orgaodao = new OrgaoDAO();
						orgao = orgaodao.buscarPorNome(excel.getColuna4(), excel.getColuna5(), excel.getColuna6());
						if (orgao == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Órgão não localizado: " + excel.getColuna4() + " " + excel.getColuna5()
									+ " - " + excel.getColuna6());
							errosExcel.add(erros);
							orgaoLocalizado = 0L;
							orgaoOK = false;
						} else {
							orgaoLocalizado = orgao.getCodigo();
							orgaoLocalizadoNome = orgao.getNumero() + " " + orgao.getNome() + " - "
									+ orgao.getCidade().getNome();
							orgaoOK = true;
						}

						// VALIDANDO CIDADE
						CidadeDAO cidadedao = new CidadeDAO();
						cidade = cidadedao.buscarPorNome(excel.getColuna6());
						if (cidade == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Cidade não localizado: " + excel.getColuna6());
							errosExcel.add(erros);
							cidadeLocalizada = 0L;
							cidadeOK = false;
						} else {
							cidadeLocalizada = cidade.getCodigo();
							cidadeLocalizadaNome = cidade.getNome();
							cidadeOK = true;
						}
						break;
					case 6: // VALIDANDO ESTADO
						excel.setColuna7(cell.getStringCellValue());
						EstadoDAO estadodao = new EstadoDAO();
						estado = estadodao.buscarPorNome(excel.getColuna7());
						if (estado == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Estado não localizado: " + excel.getColuna7());
							errosExcel.add(erros);
							estadoLocalizado = 0L;
							estadoOK = false;
						} else {
							estadoLocalizado = estado.getCodigo();
							estadoLocalizadoNome = estado.getSigla();
							estadoOK = true;
						}
						break;
					case 7: // NÚMERO PROCESSO
						cell.setCellType(CellType.STRING);
						excel.setColuna8(cell.getStringCellValue());
						break;
					case 8: // NÚMERO INTERNO
						cell.setCellType(CellType.STRING);
						excel.setColuna9(cell.getStringCellValue());
						break;
					case 9: // NÚMERO CONSULTA
						cell.setCellType(CellType.STRING);
						excel.setColuna10(cell.getStringCellValue());
						break;
					case 10: // NOME AUTOR
						cell.setCellType(CellType.STRING);
						excel.setColuna11(cell.getStringCellValue());
						break;
					case 11: // EMPRESA
						excel.setColuna12(cell.getStringCellValue());
						EmpresaDAO empresadao = new EmpresaDAO();
						empresa = empresadao.buscarPorNome(excel.getColuna12());
						if (empresa == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Empresa não localizada: " + excel.getColuna12());
							errosExcel.add(erros);
							empresaLocalizada = 0L;
							empresaOK = false;
						} else {
							empresaLocalizada = empresa.getCodigo();
							empresaLocalizadaNome = empresa.getNomeResumido();
							empresaOK = true;
						}
						break;
					case 12: // TIPO DE AUDIÊNCIA
						excel.setColuna13(cell.getStringCellValue());
						TipoAudienciaDAO tdao = new TipoAudienciaDAO();
						tipoAudiencia = tdao.buscarPorNome(excel.getColuna13());
						if (tipoAudiencia == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Tipo de audiência não localizada: " + excel.getColuna13());
							errosExcel.add(erros);
							tipoAudienciaLocalizado = 0L;
							tipoAudienciaOK = false;
						} else {
							tipoAudienciaLocalizado = tipoAudiencia.getCodigo();
							tipoAudienciaLocalizadoNome = tipoAudiencia.getNome();
							tipoAudienciaOK = true;
						}
						break;
					case 13: // ORIENTAÇÕES
						cell.setCellType(CellType.STRING);
						excel.setColuna14(cell.getStringCellValue());
						break;
					case 14: // ESCRITÓRIO
						excel.setColuna15(cell.getStringCellValue());
						EscritorioDAO edao = new EscritorioDAO();
						escritorio = edao.buscarPorNome(excel.getColuna15());
						if (escritorio == null) {
							erros.setLinha(row.getRowNum() + ", " + cell.getColumnIndex());
							erros.setNome("Escritório não localizado: " + excel.getColuna15());
							escritorioLocalizado = 0L;
							escritorioOK = false;
						} else {
							escritorioLocalizado = escritorio.getCodigo();
							escritorioLocalizadoNome = escritorio.getNomeResumido();
							escritorioOK = true;
						}
						break;
					}
				}

				// VERIFICANDO AS VALIDAÇÕES ESTÃO TODAS OK
				if ((correspondenteOK == true) && (orgaoOK == true) && (cidadeOK == true) && (estadoOK == true)
						&& (empresaOK == true) && (escritorioOK == true) && (tipoAudienciaOK == true)) {
					System.out.println("TUDO OK - INSERIR: Nr. Processo: " + excel.getColuna8() + " | Correspondente: "
							+ correspondenteLocalizadoNome + " | Dia/Hora: " + excel.getColuna2() + "/ "
							+ excel.getColuna3() + " | Órgão: " + orgao + " | Empresa: " + empresaLocalizadaNome
							+ " | TA: " + tipoAudienciaLocalizadoNome + " | Escritorio: " + escritorioLocalizadoNome);

					// VERIFICAR DUPLICIDADE BASEADO NO NÚMERO DO PROCESSO
					AudienciaDAO audienciaDAO = new AudienciaDAO();
					listaResultado = audienciaDAO.buscarPorNumeroProcesso(excel.getColuna8());
					if (listaResultado.size() == 0) {
						AudienciaBean aBean = new AudienciaBean();
						aBean.importarAudiencia(excel.getColuna2(), excel.getColuna3(), orgao, tipoAudiencia, empresa,
								escritorio, correspondente, excel.getColuna8(), excel.getColuna9(), excel.getColuna10(),
								excel.getColuna11());
					} else {
						duplicados.setLinha("" + row.getRowNum());
						duplicados.setNome("Nr. Processo: " + excel.getColuna8() + " | Correspondente: "
								+ correspondenteLocalizadoNome + " | Dia/Hora: " + excel.getColuna2() + "/ "
								+ excel.getColuna3() + " |Órgão: " + orgaoLocalizadoNome + " | Empresa: "
								+ empresaLocalizadaNome + " | TA: " + tipoAudienciaLocalizadoNome + " | Escritorio: "
								+ escritorioLocalizadoNome);
						excelDuplicado.add(duplicados);
					}

				} else {
					System.out.println("Verificar a aba de ERROS.");
				}

				// VERIFICAR DUPLICIDADE BASEADO NO NÚMERO DO PROCESSO
				// AudienciaDAO audienciaDAO = new AudienciaDAO();
				// listaResultado = audienciaDAO.buscarPorNome(excel.getColuna1(),
				// excel.getColuna2(), excel.getColuna3(),
				// orgaoLocalizado, empresaLocalizada, tipoAudienciaLocalizado,
				// escritorioLocalizado);
				//// listaResultado = audienciaDAO.buscarPorNumeroProcesso(excel.getColuna8());
				//
				// if (listaResultado.size() == 0) {
				// System.out.println(
				// "Nr. Processo: " + excel.getColuna8() + " | Correspondente: " +
				// correspondenteLocalizadoNome
				// + " | Dia/Hora: " + excel.getColuna2() + "/ " + excel.getColuna3() + "
				// |Órgão: "
				// + orgaoLocalizadoNome + " | Empresa: " + empresaLocalizadaNome + " | TA: "
				// + tipoAudienciaLocalizadoNome + " | Escritorio: " +
				// escritorioLocalizadoNome);
				// } else {
				// duplicados.setLinha(
				// "Nr. Processo: " + excel.getColuna8() + " | Correspondente: " +
				// correspondenteLocalizadoNome
				// + " | Dia/Hora: " + excel.getColuna2() + "/ " + excel.getColuna3() + "
				// |Órgão: "
				// + orgaoLocalizadoNome + " | Empresa: " + empresaLocalizadaNome + " | TA: "
				// + tipoAudienciaLocalizadoNome + " | Escritorio: " +
				// escritorioLocalizadoNome);
				// }
				uploadsExcel.add(excel);
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		}
		return uploadsExcel;
	}

	public ArrayList<ErrosExcel> listarErros() {
		return errosExcel;
	}

	public ArrayList<ExcelDuplicado> listarDuplicados() {
		return excelDuplicado;
	}
}