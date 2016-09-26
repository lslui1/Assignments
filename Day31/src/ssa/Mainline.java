package ssa;

public class Mainline {
	
	public static void main(String[] args) {


		Major.InsertMajor("Basket Weaving");
	    Major.InsertMajor("American Ninja Chess");
	    Major.InsertMajor("Renaissance Art of the Circle");
	    Major.InsertMajor("Don't Delete Me");

	    Major.UpdateMajorDescription("Basket Weaving", "Advanced Basket Weaving");

	    Major.DeleteMajor("Don't Delete Me");

	    Major.DisplayAllMajors();
			
	}
	
}
