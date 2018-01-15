package com.logusit.softjur.bean;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.UploadDAO;
import com.logusit.softjur.domain.ErrosExcel;
import com.logusit.softjur.domain.Excel;
import com.logusit.softjur.domain.ExcelDuplicado;
import com.logusit.softjur.domain.Upload;

import jxl.read.biff.BiffException;

@ManagedBean
@ViewScoped
public class UploadBean {

	private Upload upload;
	private List<Upload> uploads;
	private ArrayList<Excel> itensExcel;
	private ArrayList<ErrosExcel> itensErros;
	private ArrayList<ExcelDuplicado> itensDuplicados;

	private Part arquivo;

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public ArrayList<Excel> getItensExcel() {
		return itensExcel;
	}

	public void setItensExcel(ArrayList<Excel> itensExcel) {
		this.itensExcel = itensExcel;
	}

	public ArrayList<ErrosExcel> getItensErros() {
		return itensErros;
	}

	public void setItensErros(ArrayList<ErrosExcel> itensErros) {
		this.itensErros = itensErros;
	}

	public ArrayList<ExcelDuplicado> getItensDuplicados() {
		return itensDuplicados;
	}

	public void setItensDuplicados(ArrayList<ExcelDuplicado> itensDuplicados) {
		this.itensDuplicados = itensDuplicados;
	}

	@PostConstruct
	public void listar() {
		try {
			UploadDAO uploadDAO = new UploadDAO();
			uploads = uploadDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os arquivos.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		upload = new Upload();
		upload.setDataCadastro(new Date());
	}

	public void salvar() {
		try {
			upload();

			Date data = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmm");
			String dataArquivo = dt.format(data);
			
			UploadDAO udao = UploadDAO.getInstance();
			upload.setNome(dataArquivo + udao.extractFileName(arquivo));

			UploadDAO uploadDAO = new UploadDAO();
			uploadDAO.merge(upload);

			upload = new Upload();
			uploads = uploadDAO.listar();

			Messages.addGlobalInfo("Arquivo salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o arquivo.");
			erro.printStackTrace();
		}
	}

	public void upload() {
		try {

			UploadDAO uploadDAO = UploadDAO.getInstance();
			uploadDAO.write(arquivo);

			// funcionario.setFoto(upload.extractFileName(uploadedPhoto));
			Messages.addGlobalInfo("Upload realizado com sucesso.");

		} catch (IOException e) {
			Messages.addGlobalInfo("Erro ao realizar o upload.");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			upload = (Upload) evento.getComponent().getAttributes().get("arquivoSelecionado");

			UploadDAO uploadDAO = new UploadDAO();
			uploadDAO.excluir(upload);

			uploads = uploadDAO.listar();

			Messages.addGlobalInfo("Arquivo removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o arquivo.");
			erro.printStackTrace();
		}
	}

	public void listarExcel(ActionEvent evento) throws BiffException, IOException, ParseException {
		try {
			upload = (Upload) evento.getComponent().getAttributes().get("arquivoSelecionado");

			UploadDAO dao = new UploadDAO();
			itensExcel = dao.listarExcel(upload);
			itensErros = dao.listarErros();
			itensDuplicados = dao.listarDuplicados();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao listar o Excel.");
			erro.printStackTrace();
		}
	}
}
