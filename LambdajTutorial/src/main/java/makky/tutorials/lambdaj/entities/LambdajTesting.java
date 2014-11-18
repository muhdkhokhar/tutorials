package makky.tutorials.lambdaj.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

public class LambdajTesting {

	public static void main(String[] args) {

		// sorting...

		LambdajTesting lambdajTesting = new LambdajTesting();

		List<Person> buildTestPersonList = lambdajTesting.buildTestPersonList();

		System.out.println(buildTestPersonList);
		
		System.out.println("\n======================Sorting==============================================\n");


		// sort by name
		List<Person> sortedPerson = Lambda.sort(buildTestPersonList,
				Lambda.on(Person.class).getName());

		System.out.println("after sorted....");

		System.out.println(sortedPerson);
		
		System.out.println("\n======================Grouping==============================================\n");


		// grouping...

		Group<Person> personGrouped = Lambda.group(buildTestPersonList,
				Lambda.by(Lambda.on(Person.class).getName()));

		// key set

		Set<String> personNameKeySet = personGrouped.keySet();

		for (String key : personNameKeySet) {

			List<Person> totalPersonInGroup = personGrouped.find(key);

			System.out.println("For name = " + key + "Total person = "
					+ totalPersonInGroup.size());
		}
		
		//extraction...
		System.out.println("\n======================Extraction==============================================\n");
		
		List<String> peronNameExtracted = Lambda.extract(buildTestPersonList, Lambda.on(Person.class).getName());
		
		System.out.println("Extracted person names = " + peronNameExtracted);
		
		//index...
		System.out.println("\n======================Index==============================================\n");
		
		Map<Integer, Person> indexedMap = Lambda.index(buildTestPersonList, Lambda.on(Person.class).getAge());
		
		System.out.println("Indexed map = " + indexedMap);
		
		//filtering...
		System.out.println("\n======================filtering==============================================\n");
		
		//filter where person has age over 22
		
		List<Person> filteredPerson = Lambda.filter(Lambda.having(Lambda.on(Person.class).getAge(),Matchers.greaterThan(40)), buildTestPersonList);
		
		System.out.println("Person aged over 40  = " + filteredPerson);
		
		System.out.println("\n======================projecting...==============================================\n");
		
		List<PersonDTO> projectedPerson = Lambda.project(buildTestPersonList, PersonDTO.class,Lambda.on(Person.class).getName(),Lambda.on(Person.class).getAge());
		
		System.out.println("projected person =" + projectedPerson);
		
		

	}

	private List<Person> buildTestPersonList() {
		ArrayList<Person> personList = new ArrayList<Person>();
		// add some test
		personList.add(new Person("John", "New York", 22));
		personList.add(new Person("Matthew", "Sinagpre", 52));
		personList.add(new Person("Robert", "London", 42));
		personList.add(new Person("Muhammad", "Glasgow", 28));
		personList.add(new Person("Maciej", "Poland", 32));
		personList.add(new Person("Andrew", "Chicago", 12));
		personList.add(new Person("Pat", "Mumbai", 32));
		personList.add(new Person("Pat", "Mumbai", 32));

		return personList;
	}

}
