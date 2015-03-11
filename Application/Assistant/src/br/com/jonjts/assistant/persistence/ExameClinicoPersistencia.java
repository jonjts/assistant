/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jonjts.assistant.persistence;

import br.com.jonjts.assistant.entity.ExameClinico;
import java.sql.SQLException;

/**
 *
 * @author Jonas
 */
public class ExameClinicoPersistencia extends GenericPersistencia<ExameClinico, Long>{

    public ExameClinicoPersistencia() throws SQLException {
        super(DAO.instance().getConnectionSource(), ExameClinico.class);
    }
    
}
