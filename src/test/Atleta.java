package test;

import javax.swing.JOptionPane;

/**
 *
 * @author Ebenezer-Smart
 */
public class Atleta {
//Atributos ou características da classe
	private String nome;
	private String nacionalidade;
	private int derrotas;
	private int vitorias;
	private int empate;
	private float altura;
	private float peso;
	private int idade;
	private String categoraria;

//Construtor;
	public Atleta() {

	}

	public Atleta(String no, String na, int id, float pe, float al, int v, int e, int d) {
		this.nome = no;
		this.nacionalidade = na;
		this.altura = al;
		this.derrotas = d;
		this.empate = e;
		this.idade = id;
		this.vitorias = v;
		this.setPeso(pe);
	}

//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String no) {
		this.nome = no;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getEmpate() {
		return empate;
	}

	public void setEmpate(int empate) {
		this.empate = empate;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float pe) {
		this.peso = pe;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCategoraria() {
		return categoraria;
	}

	public void setCategoraria(String categoraria) {
		this.categoraria = categoraria;
	}

	public String verificaCategoraria(double peso) {
		String retorno = null;
		if (this.peso < 62.3f) {
			retorno = "Inválido";
		} else if (this.peso <= 78.3f) {
			retorno = "Leve";
		} else if (this.peso <= 87.9f) {
			retorno = "Médio";
		} else if (this.peso <= 101.2f) {
			retorno = "Pesado";
		} else {
			retorno = "Inválido";
		}
		return retorno;
	}

	public void perder() {
		this.derrotas++;
	}

	public void ganharLuta() {
		this.setVitorias(this.getVitorias() + 1);
	}

	public void empatarLuta() {
		this.empate += 1;
	}

	public void apresentar() {
		JOptionPane.showMessageDialog(null,
				"<html>Sobre Atleta:<hr>Nome: " + this.getNome() + "<hr>Nacionalidade: " + this.getNacionalidade()
						+ "<hr>Idade: " + this.getIdade() + " anos<hr>Altura: " + "" + this.getAltura()
						+ "m <hr>Categoria: " + this.getCategoraria() + "<hr>Peso: " + this.getPeso()
						+ "Kg <hr>Derrotas: " + this.getDerrotas() + "<hr>Empates: " + this.getEmpate()
						+ "<hr>Vitórias: " + this.getVitorias() + "</html>");
		JOptionPane.showMessageDialog(null, "Categoria: " + this.getCategoraria());
	}
}