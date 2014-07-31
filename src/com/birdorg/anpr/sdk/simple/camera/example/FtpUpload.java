package com.birdorg.anpr.sdk.simple.camera.example;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import java.io.File;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FtpUpload extends Activity implements OnClickListener {
    
	
	/*********  work only for Dedicated IP ***********/
	static final String FTP_HOST= "ftp.codevsoft.net";
	
	/*********  FTP USERNAME ***********/
	static final String FTP_USER = "codevsof";
	
	/*********  FTP PASSWORD ***********/
	static final String FTP_PASS  ="82,LD.kf%Rtemi";

	Kontrollo ko = new Kontrollo();
	Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ftp);


        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
        
    }
    
    public void onClick(View v) {
		
    	/********** Pick file from sdcard *******/
        File f = new File("/sdcard/sdk/example/images/26-07-2014/YX171EK.jpg");//  "/sdcard/logo.png");

        // Upload sdcard file
        uploadFile(f);
        /********** Pick file from sdcard *******/
        File f2 = new File("26-07-2014/");//  "/sdcard/logo.png");


        // Upload sdcard file
        uploadFile(f);
		
	}
    
    public void uploadFile(File fileName){
    	
    	
		 FTPClient client = new FTPClient();
		 
		try {
			
			client.connect(FTP_HOST,21);
			client.login(FTP_USER, FTP_PASS);
			client.setType(FTPClient.TYPE_BINARY);

			client.changeDirectory("/mdoklea/");
            client.createDirectory("26-07-2014");

            client.changeDirectory("/mdoklea/26-07-2014/");
			client.upload(fileName, new MyTransferListener());
           // client.upload(f2);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				client.disconnect(true);	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
    }
    
    /*******  Used to file upload and show progress  **********/
    
    public class MyTransferListener implements FTPDataTransferListener {

    	public void started() {
    		
    		btn.setVisibility(View.GONE);
    		// Transfer started
    		Toast.makeText(getBaseContext(), " Upload Started ...", Toast.LENGTH_SHORT).show();
    		//System.out.println(" Upload Started ...");
    	}

    	public void transferred(int length) {
    		
    		// Yet other length bytes has been transferred since the last time this
    		// method was called
    		Toast.makeText(getBaseContext(), " transferred ..." + length, Toast.LENGTH_SHORT).show();
    		//System.out.println(" transferred ..." + length);
    	}

    	public void completed() {
    		
    		btn.setVisibility(View.VISIBLE);
    		// Transfer completed
    		
    		Toast.makeText(getBaseContext(), " completed ...", Toast.LENGTH_SHORT).show();
    		//System.out.println(" completed ..." );
    	}

    	public void aborted() {
    		
    		btn.setVisibility(View.VISIBLE);
    		// Transfer aborted
    		Toast.makeText(getBaseContext()," transfer aborted , please try again...", Toast.LENGTH_SHORT).show();
    		//System.out.println(" aborted ..." );
    	}

    	public void failed() {
    		
    		btn.setVisibility(View.VISIBLE);
    		// Transfer failed
    		System.out.println(" failed ..." );
    	}

    }
	

}