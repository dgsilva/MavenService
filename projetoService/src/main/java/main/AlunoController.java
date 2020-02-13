package main;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//Controller do Aluno
public class AlunoController {
     @Autowired
	AlunoRepository repository;
     
     @GetMapping("alunos")
     public ResponseEntity<?>listagem(){
    	 try{
    		 List<Aluno>lista = repository.findAll();
    		 return  ResponseEntity.ok(lista);
    	 }catch (Exception ex) {
			return new ResponseEntity<String>("erro :" + ex.getMessage(), HttpStatus.CONFLICT);
		}
     }
    	 @GetMapping("teste")
    	 @ResponseBody
    	 public String initTeste(){
    		 return "teste";
    	 }
     @GetMapping("init")
     public  ResponseEntity<?>init(){
    	 return  ResponseEntity.ok("Seja Bem Vindo ao spring boot");
     }
     
     @PostMapping(value = "aluno", consumes = MediaType.APPLICATION_JSON_VALUE,
    		 produces=MediaType.APPLICATION_JSON_VALUE)
     
     public ResponseEntity<?>gravar(@RequestBody Aluno aluno){
    	 try{
    		 aluno.gerarMedia();
    		 aluno.gerarSituacao();
    		 repository.save(aluno);
    		 return ResponseEntity.ok(aluno);
    	 }catch (Exception ex) {
    		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
    				 
			// TODO: handle exception
		}
     }
}
