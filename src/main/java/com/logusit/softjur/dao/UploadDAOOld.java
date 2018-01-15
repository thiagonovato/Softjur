package com.logusit.softjur.dao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import com.logusit.softjur.domain.Audiencia;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Correspondente;
import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.domain.ErrosExcel;
import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.domain.Estado;
import com.logusit.softjur.domain.Excel;
import com.logusit.softjur.domain.Orgao;
import com.logusit.softjur.domain.TipoAudiencia;
import com.logusit.softjur.domain.Upload;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UploadDAOOld extends GenericDAO<Upload> {

	ArrayList<ErrosExcel> errosExcel = new ArrayList<>();

	private static final UploadDAO INSTANCE = new UploadDAO();

	private List<Audiencia> listaResultado;

	// private UploadDAO() {}

	public void write(Part part) throws IOException {
		String fileName = extractFileName(part);
		Date data = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		String dataPasta = dt.format(data);

		String filePath = "C:\\Desenvolvimento\\Workspace\\LogJur\\src\\main\\webapp\\resources\\upload\\" + dataPasta;

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

		// Converter a data do arquivo para o formato da pasta
		Date data = upload.getDataCadastro();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		String dataPasta = dt.format(data);

		Workbook workbook = Workbook
				.getWorkbook(new File("C:\\Desenvolvimento\\Workspace\\LogJur\\src\\main\\webapp\\resources\\upload\\"
						+ dataPasta + "\\" + arquivoExcel));
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		ArrayList<Excel> uploadsExcel = new ArrayList<>();
		for (int indice = 1; indice < linhas; indice++) {
			Excel excel = new Excel();
			ErrosExcel erros = new ErrosExcel();

			Cell a = sheet.getCell(0, indice);
			Cell b = sheet.getCell(1, indice);
			Cell c = sheet.getCell(2, indice);
			Cell d = sheet.getCell(3, indice);
			Cell e = sheet.getCell(4, indice);
			Cell f = sheet.getCell(5, indice);
			Cell g = sheet.getCell(6, indice);
			Cell h = sheet.getCell(7, indice);
			Cell i = sheet.getCell(8, indice);
			Cell j = sheet.getCell(9, indice);
			Cell k = sheet.getCell(10, indice);
			Cell l = sheet.getCell(11, indice);
			Cell m = sheet.getCell(12, indice);
			Cell n = sheet.getCell(13, indice);
			Cell o = sheet.getCell(14, indice);

			String a1 = a.getContents().toString();
			String a2 = b.getContents().toString();
			String a3 = c.getContents().toString();
			String a4 = d.getContents().toString();
			String a5 = e.getContents().toString();
			String a6 = f.getContents().toString();
			String a7 = g.getContents().toString();
			String a8 = h.getContents().toString();
			String a9 = i.getContents().toString();
			String a10 = j.getContents().toString();
			String a11 = k.getContents().toString();
			String a12 = l.getContents().toString();
			String a13 = m.getContents().toString();
			String a14 = n.getContents().toString();
			String a15 = o.getContents().toString();

			excel.setColuna1(a1);
			excel.setColuna2(a2);
			excel.setColuna3(a3);
			excel.setColuna4(a4);
			excel.setColuna5(a5);
			excel.setColuna6(a6);
			excel.setColuna7(a7);
			excel.setColuna8(a8);
			excel.setColuna9(a9);
			excel.setColuna10(a10);
			excel.setColuna11(a11);
			excel.setColuna12(a12);
			excel.setColuna13(a13);
			excel.setColuna14(a14);
			excel.setColuna15(a15);

			// Validando o Correspondente
			CorrespondenteDAO cdao = new CorrespondenteDAO();
			Correspondente correspondente = new Correspondente();
			correspondente = cdao.buscarPorNome(a1);
			if (correspondente == null) {
				erros.setLinha("" + indice);
				erros.setNome("Correspondente não localizado: " + a1);
				errosExcel.add(erros);
			}

			// Validando o Órgão
			OrgaoDAO orgaodao = new OrgaoDAO();
			Orgao orgao = new Orgao();
			orgao = orgaodao.buscarPorNome(a4, a5, a6);
			if (orgao == null) {
				erros.setLinha("" + indice);
				erros.setNome("Órgão não localizado: " + a4 + " " + a5 + " - " + a6);
				errosExcel.add(erros);
			}

			// Validando a Cidade
			CidadeDAO cidadedao = new CidadeDAO();
			Cidade cidade = new Cidade();
			cidade = cidadedao.buscarPorNome(a6);
			if (cidade == null) {
				erros.setLinha("" + indice);
				erros.setNome("Cidade não localizado: " + a6);
				errosExcel.add(erros);
			}

			// Validando o Estado
			EstadoDAO estadodao = new EstadoDAO();
			Estado estado = new Estado();
			estado = estadodao.buscarPorNome(a7);
			if (estado == null) {
				erros.setLinha("" + indice);
				erros.setNome("Estado não localizado: " + a7);
				errosExcel.add(erros);
			}

			// Validando a Empresa
			EmpresaDAO empresadao = new EmpresaDAO();
			Empresa empresa = new Empresa();
			empresa = empresadao.buscarPorNome(a12);
			if (empresa == null) {
				erros.setLinha("" + indice);
				erros.setNome("Empresa não localizada: " + a12);
				errosExcel.add(erros);
			}
			// Validando o Tipo de Audiência
			TipoAudienciaDAO tdao = new TipoAudienciaDAO();
			TipoAudiencia tipoAudiencia = new TipoAudiencia();
			tipoAudiencia = tdao.buscarPorNome(a13);
			if (tipoAudiencia == null) {
				erros.setLinha("" + indice);
				erros.setNome("Tipo de Audiência não localizada: " + a13);
				errosExcel.add(erros);
			}

			// Validando o Escritório
			EscritorioDAO edao = new EscritorioDAO();
			Escritorio escritorio = new Escritorio();
			escritorio = edao.buscarPorNome(a15);
			if (escritorio == null) {
				erros.setLinha("" + indice);
				erros.setNome("Escritório não localizado: " + a15);
				errosExcel.add(erros);
			}

			// VERIFICANDO SE HÁ ESSA AUDIÊNCIA CADASTRADA		
			AudienciaDAO audienciaDAO = new AudienciaDAO();

			if (listaResultado.size() == 0) {
				System.out.println("Não achou Correspondente: " + a1);
			} else {
				System.out.println("Achou " + listaResultado.size() + " audiências para Correspondente " + a1);
			}
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			String dataFormato = formato.format(a2);
			System.out.println("Data: " + dataFormato);

			uploadsExcel.add(excel);
		}
		workbook.close();

		return uploadsExcel;
	}

	public ArrayList<ErrosExcel> listarErros() {
		return errosExcel;
	}
}