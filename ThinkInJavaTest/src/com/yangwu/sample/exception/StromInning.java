package com.yangwu.sample.exception;

public class StromInning extends Inning implements Storm {

	
	public StromInning() throws BaseballException ,RainedOut,Foul{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void event()  {
		// TODO Auto-generated method stub
		try {
			super.event();
		} catch (BaseballException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rainHard() throws RainedOut {
		// TODO Auto-generated method stub

	}

	@Override
	public void atBat() throws Foul, Strike {
		// TODO Auto-generated method stub

	}

}
