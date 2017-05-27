package br.com.code4u;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController
{
  public EndpointController() {}
  
  public static List<Dispositivo> dispositivos = Arrays.asList(new Dispositivo[] { new Dispositivo(1, "Lampada", false) });
  
  @RequestMapping({"/ligar/{id}"})
  public void ligar(@PathVariable("id") long id) {
    System.out.println("Ligando o id " + id);
    for (Dispositivo disp : dispositivos) {
      if (disp.getId() == id) {
        disp.setStatus(true);
      }
    }
    ligarEDesligarRelay(" ligar");
  }
  
  @RequestMapping({"/desligar/{id}"})
  public void desligar(@PathVariable("id") long id) {
    System.out.println("Desligando o id " + id);
    for (Dispositivo disp : dispositivos) {
      if (disp.getId() == id) {
        disp.setStatus(false);
      }
    }
    ligarEDesligarRelay(" desligar");
  }
  
  private void ligarEDesligarRelay(String comando) {
    try {
      Socket clientSocket = new Socket();
      clientSocket.connect(new java.net.InetSocketAddress("192.168.0.35", 6789), 5000);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      outToServer.writeBytes(comando + '\n');
      clientSocket.close();
    } catch (Exception e) {
      System.out.println("Erro Comunicação ARDUINO");
    }
  }
  
  @RequestMapping({"/listar"})
  public List<Dispositivo> listar() {
    return dispositivos;
  }
}
