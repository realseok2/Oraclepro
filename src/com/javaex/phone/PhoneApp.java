package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PhoneDao phoneDao = new PhoneDao();
		boolean run = true;

		String insertName;
		String insertHp;
		String insertCompany;
		String search = null;
		int selectNo = 0;

//-----------------------------------------------------------------------------------------

		System.out.println("************************************************");
		System.out.println("*                           전화번호 관리 프로그램  V.01                         *");
		System.out.println("************************************************");

//-----------------------------------------------------------------------------------------		

		while (run)

		{
			System.out.println("--------------------------------------------------------------");
			System.out.println("1. 리스트  |  2. 등록  |  3. 수정  |  4. 삭제  |  5. 검색  |  6. 종료");
			System.out.println("--------------------------------------------------------------");
			System.out.print(">메뉴번호 : ");
			int menuNo = sc.nextInt();

//-----------------------------------------------------------------------------------------			

			switch (menuNo) {

			case 1:
				// 리스트
				System.out.println("< 1. 리스트 >");
				System.out.println("리스트를 출력합니다.");

				List<PersonVo> personList = phoneDao.getPersonList();
				System.out.println("=================================");
				for (PersonVo vo : personList) {
					System.out.println(
							vo.getPersonId() + ",   " + vo.getName() + ",   " + vo.getHp() + ",   " + vo.getCompany());
				}
				System.out.println("=================================");
				break;

			case 2:
				// 등록
				System.out.println("< 2. 등록 >");
				System.out.println("기존의 번호 목록에 새로운 번호를 추가합니다.");

				sc.nextLine();
				System.out.print(">이름 :");
				insertName = sc.nextLine();
				System.out.print(">전화번호 :");
				insertHp = sc.nextLine();
				System.out.print(">회사전화번호 :");
				insertCompany = sc.nextLine();

				PersonVo insertVo = new PersonVo(insertName, insertHp, insertCompany);
				phoneDao.personInsert(insertVo);
				break;

			case 3:
				// 수정
				System.out.println("< 3. 수정 >");
				System.out.println("기존의 번호 목록을 선택된 번호를 수정합니다.");
				System.out.println("수정할 번호를 입력해 주세요.");

				System.out.print(">번호 : ");
				selectNo = sc.nextInt();
				sc.nextLine();
				System.out.print(">이름 :");
				insertName = sc.nextLine();
				System.out.print(">전화번호 :");
				insertHp = sc.nextLine();
				System.out.print(">회사전화번호 :");
				insertCompany = sc.nextLine();

				PersonVo updateVo = new PersonVo(selectNo, insertName, insertHp, insertCompany);
				phoneDao.personUpdate(updateVo);

				break;

			case 4:
				// 삭제
				System.out.println("< 4. 삭제 >");
				System.out.println("기존의 번호 목록에서 선택된 번호를 삭제합니다.");

				System.out.print(">번호 : ");
				selectNo = sc.nextInt();
				phoneDao.personDelete(selectNo);

				break;

			case 5:
				// 검색
				sc.nextLine();
				System.out.println("< 5. 검색 >");
				System.out.println("검색할 글자 혹은 숫자를 입력해 주세요.");

				System.out.print(">입력 : ");
				search = sc.nextLine();
				
				
//				phoneDao.personSearch(search);
//				List<PersonVo> searchList = phoneDao.getPersonList();		--> 여기서 전체리스트를 출력하는 오류를 범하고 있었음
				
				List<PersonVo> searchList = phoneDao.personSearch(search);
				System.out.println("=================================");
				for (PersonVo vo : searchList) {
					System.out.println(
							vo.getPersonId() + ",   " + vo.getName() + ",   " + vo.getHp() + ",   " + vo.getCompany());
				}
				System.out.println("=================================");

				break;

			case 6:
				// 종료
				System.out.println("< 6. 종료 >");
				System.out.println("************************************************");
				System.out.println(
						"*                                          감사합니다.                                          *");
				System.out.println("************************************************");
				run = false;
				break;

			default:
				// 이외의 번호 입력
				System.out.println("잘못 입력하셨습니다. 1 ~6 사이의 번호만 입력해 주세요.");
				break;
			}

		} // while
		sc.close();
	}// main
}// class
