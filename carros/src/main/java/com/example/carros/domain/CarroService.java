package com.example.carros.domain;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros(){

        return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());

        // List<Carro> carros = rep.findAll();
        // List<CarroDTO> list = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
        // return list;
    }

    public CarroDTO getCarroById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado");
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return rep.findCarroByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());

        //  return rep.findCarroByTipo(tipo).stream().map(CarroDTO::new)).collect(Collectors.toList());
    }

    public CarroDTO save(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o carro!");
        return new CarroDTO(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = rep.findById(id);
        if(optional.isPresent()){
            Carro db = optional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            rep.save(db);

            return new CarroDTO(rep.save(db));
        }
        else{
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}


    /*
    public List<Carro> getCarrosFake(){
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L, "Fusca"));
        carros.add(new Carro(2L, "Bugatti"));
        carros.add(new Carro(3L, "Ferrari"));


        return carros;
    }
     */
}
