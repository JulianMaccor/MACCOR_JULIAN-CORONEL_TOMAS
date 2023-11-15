package com.backend.odontologia.dao.impl;

import com.backend.odontologia.dao.H2Connection;
import com.backend.odontologia.dao.IDao;
import com.backend.odontologia.model.Odontologo;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {


    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            odontologoRegistrado = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoRegistrado.setId(resultSet.getInt(1));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologoRegistrado);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Hemos tenido un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(e.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexión: " + ex.getMessage());
            }
        }

        return odontologoRegistrado;
    }

    @Override
    public Iterable<Odontologo> listar() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = crearObjOdontologo(resultSet);
                odontologos.add(odontologo);

            }
            LOGGER.info("Listado de todos los odontologos: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ocurrio un error al intentar cerrar la base de datos. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologo = crearObjOdontologo(resultSet);
            }
            LOGGER.info("Se ha encontrado el odontologo con id " + id + ":" + odontologo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ocurrio un error al cerrar la base de datos. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }

    private Odontologo crearObjOdontologo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String matricula = resultSet.getString("matricula");

        return new Odontologo(id, matricula, nombre, apellido);
    }
}