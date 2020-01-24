package test;

public class TesteAtleta {

	public static void main(String[] args) {
		Atleta atleta = new Atleta();

		atleta.setNome("Gilson");

		atleta.setPeso(100.0f);
		atleta.setCategoraria(atleta.verificaCategoraria(atleta.getPeso()));
		System.out.println(atleta.getNome());

		atleta.apresentar();
	}
}
