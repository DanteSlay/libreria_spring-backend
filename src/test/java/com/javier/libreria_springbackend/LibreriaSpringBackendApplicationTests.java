package com.javier.libreria_springbackend;

import com.javier.libreria_springbackend.models.dto.LibroDto;
import com.javier.libreria_springbackend.models.entity.Autor;
import com.javier.libreria_springbackend.models.entity.Libro;
import com.javier.libreria_springbackend.models.service.AutorService;
import com.javier.libreria_springbackend.models.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;


import java.util.List;

@SpringBootTest
class LibreriaSpringBackendApplicationTests {

	@Autowired
	AutorService autorService;

	@Autowired
	LibroService libroService;

	@Test
	void test_save_and_findAll() {
		Autor autor = new Autor("Chanchito");
		List<Autor> all = autorService.findAll();
		assertThat(all.size()).isEqualTo(2);
		autorService.save(autor);
		all = autorService.findAll();
		assertThat(all.size()).isEqualTo(3);
	}

	@Test
	void test_saveLibro_with_Autor() {

		List<LibroDto> libros = libroService.findAll();
		List<Autor> autors = autorService.findAll();

		System.out.println("Libros antes de guardar: " + libros.size());
		System.out.println("Autores antes de guardar: " + autors.size());

		Autor autor = new Autor("Chanchito");
		Libro libro = new Libro("Libro de prueba");
		libro.setAutor(autor);
		libroService.save(libro);

		libros = libroService.findAll();
		autors = autorService.findAll();

		System.out.println("Libros despues de guardar: " +libros.size());
		System.out.println("Autores despues de guardar: " +autors.size());

		assertThat(libros.size()).isEqualTo(3);
		assertThat(autors.size()).isEqualTo(3);
	}

	@Test
	void delete_Autor_Libros() {
		List<LibroDto> libros = libroService.findAll();
		List<Autor> autors = autorService.findAll();

		System.out.println("Libros antes de eliminar: " + libros.size());
		System.out.println("Autores antes de eliminar: " + autors.size());

		autorService.delete(1L);

		libros = libroService.findAll();
		autors = autorService.findAll();

		System.out.println("Libros despues de eliminar: " + libros.size());
		System.out.println("Autores despues de eliminar: " + autors.size());

	}
}
