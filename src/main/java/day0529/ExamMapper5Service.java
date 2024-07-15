package day0529;

import java.util.List;

import kr.co.sist.domain.CarDomain;

public class ExamMapper5Service {
	
	private static ExamMapper5Service ems;
	
	private ExamMapper5Service() {
		
	}
	
	public static ExamMapper5Service getInstance() {
		if(ems == null) {
			ems=new ExamMapper5Service();
		}//end if
		return ems;
	}//getInstance
	
	public List<CarDomain> searchCar( String country ){
		List<CarDomain> list = null;
		if(!"국산".equals(country)) {
			country="수입";
		}//end if
		
		ExamMapper5DAO em5DAO = ExamMapper5DAO.getInstance();
		list = em5DAO.joinSubquery(country);
		
		//car_option이 16자 이상이라면 15자리까지만 보여주고 나머지는 ...으로 처리
		
		String carOption="";
		for( CarDomain cd : list) {
			carOption = cd.getCar_option();
			if(carOption.length() > 16) {
				carOption = carOption.substring(0, 15) + " ...";
				cd.setCar_option(carOption);
			}//end if
		}//end for
		
		return list;
	}
	
//	public static void main(String[] args) {
//		System.out.println(getInstance().searchCar("국산"));
//	}
	
	
}//class
