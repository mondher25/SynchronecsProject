package service;

public class Password {
	
	
	public static String randomPassword(int longueur){
		String password="";
		
		for(int i=0;i<longueur-2;i++){
			password=password+randomCar("abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ");
		
		
	}
		String randomNumb=randomCar("0123456789");
		password=insertRandom(password,randomNumb);
		
		String randomSymbole=randomCar("*?/+-~!@$%&_-=<>#;.,");
		password=insertRandom(password,randomSymbole);
		return password;
		
}

	
	public static String randomCar(String character){
		int n=character.length();
		int r=(int)(n * Math.random());
		return character.substring(r, r+1);
	}
	
	public static String insertRandom(String str,String toInsert){
		int n=str.length();
		int r=(int)((n+1) * Math.random());
		return str.substring(0, r)+toInsert+str.substring(r);
	}
	
	
//	public static void main(String args[]){
//		String result=randomPassword(8);
//		System.out.println(result);
//	}
//	
}