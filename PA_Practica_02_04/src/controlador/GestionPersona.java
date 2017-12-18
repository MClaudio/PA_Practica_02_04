package controlador;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import modelo.Persona;

public class GestionPersona {

    private List<Persona> personas;
    private File archivo;

    public GestionPersona(String pathname) {
        archivo = new File(pathname);
        personas = new ArrayList<Persona>();
    }

    public void agregarPersona(String cedula, String nombre, String direccion, String fechaNac) throws Exception {
        RandomAccessFile file = new RandomAccessFile(archivo, "rw");
        if (nombre.length() < 22) {
            while (nombre.length() < 22) {
                nombre += " ";
            }
        }
        else if (nombre.length() > 22) {
            throw new Exception("Nombre maximo 22 caracteres");
        }

        if (direccion.length() < 28) {
            while (direccion.length() < 28) {
                direccion += " ";

            }
        }
        else if (direccion.length() > 28) {
            throw new Exception("Direccion maximo 28 caracteres");
        }

        if (nombre.length() == 22 && direccion.length() == 28) {
            try {
                file.seek(archivo.length());
                //System.out.println("Inicio: " + file.getFilePointer());
                file.writeUTF(cedula);
                file.writeUTF(nombre);
                file.writeUTF(direccion);
                file.writeUTF(fechaNac);
                //System.out.println("Fin: " + file.getFilePointer());

            } catch (Exception e) {
                throw new Exception("Error al escribir el archivo.");
            }
        }
        else {
            throw new Exception("Error no se a completado.");
        }
    }

    public List<Persona> listarPersona() throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(archivo, "r");
        try {
            while (true) {

                Persona persona = new Persona();

                ///System.out.println("Inicio: " + file.getFilePointer());
                persona.setCedula(file.readUTF());
                persona.setNombre(file.readUTF());
                persona.setDireccion(file.readUTF());
                persona.setFechaNac(file.readUTF());

                //System.out.println("Fin: " + file.getFilePointer());
                personas.add(persona);
            }
        } catch (Exception e) {
            //System.out.println("Fin lectura.");
            return personas;
        } finally {
            file.close();
        }
    }

    public void validaFechaNac(String fechaNac) throws Exception {
        if (fechaNac.length() != 10) {
            throw new Exception("Formato de fecha incorrecta formato correcto 00/00/0000");
        }
    }

    public void validarCedula(String cedula) throws Exception {
        try {
            int d = Integer.parseInt(cedula);
        } catch (Exception e) {
            throw new Exception("la cedula debe contener numeros.");
        }
        if (cedula.length() != 10) {
            throw new Exception("la cedula debe contener 10 numeros.");
        }
    }

    public long obtieneTamanio() {
        return archivo.length();
    }

    public Persona buscaRegistro(int reg) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(archivo, "r");
        try {
            Persona persona = new Persona();
            long cursor = (reg - 1) * 78;
            System.out.println("Cursor: " + cursor);

            file.seek(cursor);
            System.out.println("Inicio: " + file.getFilePointer());

            persona.setCedula(file.readUTF());
            persona.setNombre(file.readUTF());
            persona.setDireccion(file.readUTF());
            persona.setFechaNac(file.readUTF());

            System.out.println("Fin: " + file.getFilePointer());
            return persona;
        } catch (Exception e) {
            //System.out.println("Fin lectura.");
        } finally {
            file.close();
        }
        return null;
    }

    public void editarRegistro(int reg, String cedula, String nombre, String direccion, String fechaNac) throws FileNotFoundException, IOException, Exception {
        RandomAccessFile file = new RandomAccessFile(archivo, "rw");
        if (nombre.length() < 22) {
            while (nombre.length() < 22) {
                nombre += " ";
            }
        }
        else if (nombre.length() > 22) {
            throw new Exception("Nombre maximo 22 caracteres");
        }

        if (direccion.length() < 28) {
            while (direccion.length() < 28) {
                direccion += " ";

            }
        }
        else if (direccion.length() > 28) {
            throw new Exception("Direccion maximo 28 caracteres");
        }

        if (nombre.length() == 22 && direccion.length() == 28) {
            try {

                file.seek((reg - 1) * 78);
                //System.out.println("Inicio: " + file.getFilePointer());

                file.writeUTF(cedula);
                file.writeUTF(nombre);
                file.writeUTF(direccion);
                file.writeUTF(fechaNac);
                file.seek(0);

                System.out.println("Fin: " + file.getFilePointer());
                file.close();
                //System.out.println("Fin: " + file.getFilePointer());

            } catch (Exception e) {
                throw new Exception("Error al escribir el archivo.");
            }
        }
        else {
            throw new Exception("Error no se a completado.");
        }

        try {

        } catch (Exception e) {
            System.out.println("Fin Escritura error. " + e.getMessage());
        }
    }

    public void vailidaNumeros(String cadena) throws Exception {
        try {
            int n = Integer.parseInt(cadena);
        } catch (Exception e) {
            throw new Exception("Error campo numerico.");
        }
    }

    public List<Persona> getPersonas() {
        return personas;
    }
}
