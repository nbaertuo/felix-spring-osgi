import java.util.logging.Logger;

import org.ertuo.linliqin.service.tools.PullTool;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.lang.StringUtils;
import org.ertuo.linliqin.domain.hr.Hr;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;



public class HrController {
	private static final Logger logger = Logger.getLogger(HrController.class.getName());

	EntityManagerFactory entityManagerFactory 

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();


	def list={
			EntityManager em=entityManagerFactory.createEntityManager()
			def hrs=em.createQuery("select h from Hr h").getResultList()
			return [hrs:hrs]
	}

	def edit={
			
		Hr hr=new Hr()
		bindData(hr,params)
		EntityManager em=entityManagerFactory.createEntityManager()
		request.getParameterMap().each{
			println(it.key+'=='+it.value)
		}
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("myFile");
        println(blobKey)

		/*String inputDateString = "${params.startDate_year}/${params.startDate_month}/${params.startDate_day}"
        Date inputDate = new SimpleDateFormat("yyyy/MM/dd").parse(inputDateString)*/
		//持久化保存
		def persist={
			try {
				em.persist(it)
			} finally {
				em.close()
			}
		}
		if(hr.hrId!=null){
			persist(hr)
			return [hr:hr]
		}
		
		if(StringUtils.isNotBlank(params.id)){
			logger.info(params.id)
			hr=(Hr)em.find(Hr.class,Long.parseLong(params.id))
			return [hr:hr]
		}
		if(hr.validate()) {
			persist(hr)
		}
		return [hr:hr]
		
	}

			        /*def createZip = {
					  byte[] zip = createZipForClient()
					  response.contentType = "application/octet-stream"
					  response.outputStream << zip
					}*/
	 

}
