package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;

public class CorrentistaRepository {
	private static Map<Integer, Correntista> repositorio = new HashMap<Integer, Correntista>();
	
	public static Correntista findById(Integer id) {
		return repositorio.get(id);
	}
	
	public static void save(Correntista correntista) {
		repositorio.put(correntista.getId(), correntista);
	}
	
	public static List<Correntista> findAll() {
		List<Correntista> correntistas = repositorio.values().stream().collect(Collectors.toList());
		return correntistas;
	}

}
