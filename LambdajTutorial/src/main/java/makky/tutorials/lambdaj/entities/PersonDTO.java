package makky.tutorials.lambdaj.entities;

public class PersonDTO {

	@Override
	public String toString() {
		return "\nPersonDTO [name=" + name + ", age=" + age + "]";
	}

	private String name;
	private int age;

	public PersonDTO() {

	}

	public PersonDTO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
