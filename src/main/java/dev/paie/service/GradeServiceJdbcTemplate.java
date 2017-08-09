package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

@Autowired
public GradeServiceJdbcTemplate(DataSource dataSource) {
super();
this.jdbcTemplate = new JdbcTemplate(dataSource);
}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		create(nouveauGrade);
	}

	@Override
	public void mettreAJour(Grade grade) {
		update(grade);
	}

	@Override
	public List<Grade> lister() {
		return findAllGrade();
	}

	public void create(Grade p) {
		String sql = "INSERT INTO grade (id,code,nbHeuresBase,tauxBase) VALUES(?,?,?,?)";
		this.jdbcTemplate.update(sql, p.getId(), p.getCode(), p.getNbHeuresBase(), p.getTauxBase());
	}

	public void update(Grade p) {
		String sql = "UPDATE grade SET code = ?,nbHeuresBase = ? ,tauxBase= ? WHERE ID = ? ";
		this.jdbcTemplate.update(sql, p.getCode(), p.getNbHeuresBase(), p.getTauxBase(), p.getId());
	}

	public List<Grade> findAllGrade() {
		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}

	public class GradeMapper implements RowMapper<Grade> {

		@Override
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
			Grade p = new Grade();
			p.setId(rs.getInt("id"));
			p.setCode(rs.getString("code"));
			p.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
			p.setTauxBase(rs.getBigDecimal("tauxBase"));
			return p;
		}
	}

}