package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	public CarroService service;

	@Test
	public void testeSave(){
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		CarroDTO c = service.save(carro);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		//Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		//Deletar o objeto
		service.delete(id);
		//Verificar o objeto
		try {
			assertNull(service.getCarroById(id));
			fail("Carro não foi excluído");
		}catch(ObjectNotFoundException e){

		}


	}

	@Test
	public void testeLista(){
		List<CarroDTO> carros = service.getCarros();
		assertEquals(30, carros.size());
	}

	@Test
	public void testGet(){
		CarroDTO c = service.getCarroById(11L);
		assertNotNull(c);
		assertEquals("Ferrari FF", c.getNome());
	}

}
