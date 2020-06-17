package com.javaex.phone;

public class PersonVo {

//----------------------------------------------------------------------------------------------			필드

	private int personId;			//	사람 코드
	private String name;				//	사람 이름
	private String hp;					//	사람 전화번호
	private String company;		//	사람 회사 전화번호

//----------------------------------------------------------------------------------------------			생성자

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

//----------------------------------------------------------------------------------------------			getter, setter



	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

//----------------------------------------------------------------------------------------------			일반 메소드

	@Override
	public String toString() {
		return "PersonVo [personId=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

}












//System.out.print(">입력 : ");
//search = sc.nextLine();
//sc.nextLine();
//
//
//
//PersonVo searchVo = new PersonVo(search);
//phoneDao.personSearch(searchVo);
