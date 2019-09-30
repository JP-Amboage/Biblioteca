CREATE TABLE usuarios_buscados
(
usuario character varying(10) NOT NULL,
prestamos_vencidos integer NOT NULL,
CONSTRAINT usuarios_fkey FOREIGN KEY (usuario)
REFERENCES usuario (id_usuario) 
)