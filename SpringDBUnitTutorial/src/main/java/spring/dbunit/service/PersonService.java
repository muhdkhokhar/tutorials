package spring.dbunit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PersonService {

	// get person count
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer countPersonByName(final String personName) {
		int total = 0;

		total = jdbcTemplate.queryForObject(
				"select count(*) from person where name=?",
				new Object[] { personName }, Integer.class);

		return total;
	}

}
