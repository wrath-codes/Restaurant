package com.wrathcodes.restaurant.seeder;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.context.FacesContext;

import com.wrathcodes.restaurant.util.HibernateUtil;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericSeeder<Entity> {
	private Class<Entity> entityClass;

	// Constructor
	@SuppressWarnings("unchecked")
	public GenericSeeder() {
		this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "resource", "deprecation" })
	public void seed(String path) throws Exception {
		System.out.println("Seeding " + entityClass.getSimpleName() + "...");
		System.out.println("Path: " + path);

		// if does not exist or is empty
		if (path == null || path.isEmpty()) {
			System.out.println("Path is empty or null!");
			return;
		}

		// Create session
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			// Convert path to InputStream
			InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
			transaction = session.beginTransaction();

			// Read CSV file
			CSVParser csvParse = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT.withHeader());
			// print header
//			System.out.println(csvParse.getHeaderMap());
//			csvParse.forEach(record -> {
//				System.out.println(record);
//			});

			// check to see if headers match entity fields
//			if (csvParse.getHeaderMap().keySet() == Set(entityClass.getDeclaredFields().toString())) {
			List<Field> fields = Arrays.asList(entityClass.getDeclaredFields());
			List<String> classFields = new ArrayList<String>();
			for (Field field : fields) {
				classFields.add(field.getName());
			}
			if (csvParse.getHeaderMap().keySet() == classFields) {
				System.out.println(csvParse.getHeaderMap().keySet());
				System.out.println(entityClass.getDeclaredFields().toString());
				System.out.println("Headers match entity fields!");
			}
			// if headers do not match entity fields
			else {
				System.out.println("Headers do not match entity fields!");
				System.out.println(csvParse.getHeaderMap().keySet());
				System.out.println(classFields);
				throw new Exception("Headers do not match entity fields!");
            }
			
			for (CSVRecord record : csvParse) {
				System.out.println(record);
			}

			
			// get records
			// close csvParse
			// Parse CSV file
			// Create entity objects
			// Save entity objects
			transaction.commit();
		} catch (RuntimeException | IOException error) {
			if (transaction != null) {
				transaction.rollback();
				throw error;
			}
		} finally {
			session.close();
		}

		System.out.println("Seeding " + entityClass.getSimpleName() + " done!");
	}

}
