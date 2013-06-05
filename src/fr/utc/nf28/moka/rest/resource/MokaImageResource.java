package fr.utc.nf28.moka.rest.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/image/{id}")
public class MokaImageResource extends MokaRestResource {
	@POST
	public Response uploadPicture(@PathParam("id") int itemId) {
		System.out.println("upload request received " + String.valueOf(itemId));

		//String uploadedFileLocation = "C:/moka/" + fileDetail.getFileName();
		// save it
		//writeToFile(uploadedInputStream, uploadedFileLocation);

		//String output = "File uploaded to : " + uploadedFileLocation;

//		File dir = new File("C:" + File.separator + "moka" + File.separator);
//		if (dir.exists()) {
//			System.out.println("dir already exist at " + dir.getAbsolutePath());
//		} else {
//			dir.mkdirs();
//			System.out.println("dir created at " + dir.getAbsolutePath());
//		}
//
//		File local = new File(dir.getAbsolutePath() +File.separator+ String.valueOf(itemId) + ".jpg");
//		if (local.exists()) {
//			System.out.println("file already exist at " + local.getAbsolutePath());
//		} else {
//			try {
//				local.createNewFile();
//				System.out.println("file created " + local.getAbsolutePath());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		try {
//			InputStream in = new FileInputStream(t);
//			OutputStream out = new FileOutputStream(local);
//			byte[] buf = new byte[1024];
//			int len;
//			while ((len = in.read(buf)) > 0){
//				out.write(buf, 0, len);
//			}
//			in.close();
//			out.close();
//			System.out.println("File copied.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		} catch (IOException e) {
//			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		}
		return Response.status(200).build();
	}

	/**
	 * @Consumes(MediaType.MULTIPART_FORM_DATA) public Response uploadImage(@PathParam("id") @FormDataParam("itemId") int itemId,
	 * @FormDataParam("file") InputStream uploadedInputStream,
	 * @FormDataParam("file") FormDataContentDisposition fileDetail) {
	 * System.out.println("upload request received" + String.valueOf(itemId));
	 * return Response.status(200).build();
	 * }  *
	 */

	@Override
	public String serialize() throws IOException {
		return "Coucou";
	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
							 String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
