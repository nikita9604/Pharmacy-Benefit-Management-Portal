package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class PatientEntryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PatientRepository patientRepository;

    // Q2 - Add Patient name, city, password & insurance id to Patient Table
    public void insertPatient(Patient patient)
    {
        String sql = "INSERT INTO PATIENT " + "(NAME, CITY, PASS, INID) VALUES (?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException
            {
                PreparedStatement ps = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, patient.getName());
                ps.setString(2, patient.getCity());
                ps.setInt(3, patient.getPass());
                ps.setInt(4, patient.getInid());
                return ps;
            }
        }, holder);

        List<Map<String, Object>> generatedEmployeeId = holder.getKeyList();
        //int generatedEmployeeId = holder.getKey().intValue();
        //System.out.println("generatedEmployeeId = " + generatedEmployeeId);
        //return patientRepository.save(patient);
    }
}
