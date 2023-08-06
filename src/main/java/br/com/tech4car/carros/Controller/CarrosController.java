package br.com.tech4car.carros.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4car.carros.Model.Carros;
import br.com.tech4car.carros.Repository.CarrosRepository;

@RestController
@RequestMapping("/veiculo")
public class CarrosController {

    @Autowired
    private CarrosRepository repositorio;
    //List <Carros>carros = new ArrayList<>();

    @GetMapping
    private List<Carros> obteCarros(){//obter todos os dados dos veiculos cadastrados
        return repositorio.findAll();
    }
    @GetMapping("/{id}")
    private Carros obterCarrosPorNome(@PathVariable String id){
        Carros carro = repositorio.findById(id).orElse(null);
        return carro;
        /*if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }*/
    }
    @PostMapping
    private ResponseEntity<String> cadastrarCarros(@RequestBody Carros carro){
        
        if(carro != null){
            repositorio.save(carro);
            String mensagem = "Categoria do Veiculo: " + carro.getTipo()
            + ".\nAutomovel: " + carro.getDescricao()
            + ".\nModelo: " + carro.getModelo()
            + ".\nAno: " + carro.getAno()
            + ".\nValor: R$ " + carro.getValor()
            + ".\nVeiculo cadastrado com sucesso!";
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);

        }
        String mensagem = "O veiculo informado e nulo.";
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")//atualizar dados
    private ResponseEntity <Carros> atualizCarros(@PathVariable String id,
                                    @RequestBody Carros carroNovo){
       Carros carro = repositorio.findById(id).orElse(null);
                                        /*Carros carro = carro.stream()
        .filter(c ->c.getDescricao().equalsIgnoreCase(id))
        .findFirst().orElse(null);*/
        if(carro != null) {
            carro.setTipo(carroNovo.getTipo());
            carro.setDescricao(carroNovo.getDescricao());
            carro.setModelo(carroNovo.getModelo());
            carro.setAno(carroNovo.getAno());
            carro.setValor(carroNovo.getValor());
            
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCarro(@PathVariable String id){
        repositorio.deleteById(id);  
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);    
        //carros.removeIf(c -> c.getDescricao().equalsIgnoreCase(descricao));

    }
}
