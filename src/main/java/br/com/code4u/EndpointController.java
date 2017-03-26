package br.com.code4u;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {
	
	public static List<Dispositivo> dispositivos = Arrays.asList(new Dispositivo(1, "Lampada", false));
	
	@RequestMapping("/ligar/{id}")
	public void ligar(@PathVariable(value="id") long id) {
		System.out.println("Ligando o id " + id);
		for(Dispositivo disp: dispositivos){
			if(disp.getId() == id){
				disp.setStatus(true);
			}
		}
		//LIGA A LAMPADA NO ARDUINO
	}

	@RequestMapping("/desligar/{id}")
	public void desligar(@PathVariable(value="id") long id) {
		System.out.println("Desligando o id " + id);
		for(Dispositivo disp: dispositivos){
			if(disp.getId() == id){
				disp.setStatus(false);
			}
		}
		//DESLIGA A LAMPADA NO ARDUINO
	}
	
	@RequestMapping("/listar")
	public List<Dispositivo> listar() {
        return dispositivos;
	}

}