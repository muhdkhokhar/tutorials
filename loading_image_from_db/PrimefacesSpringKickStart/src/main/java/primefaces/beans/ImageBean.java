package primefaces.beans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.jooq.dao.generated.tables.pojos.UserImages;
import springjooq.dao.IUserDetailService;

@Controller
@ManagedBean(name = "imageBean")
@ApplicationScoped
public class ImageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserDetailService detailService;

	public ImageBean() {

	}

	public StreamedContent getImage() {

		FacesContext fc = FacesContext.getCurrentInstance();

		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}
		String imageIdString = fc.getExternalContext().getRequestParameterMap()
				.get("imageID");

		int imgIdInt = Integer.parseInt(imageIdString);

		UserImages foundImage = detailService.findImage(imgIdInt);

		return new DefaultStreamedContent(new ByteArrayInputStream(
				foundImage.getImage()));
	}

}
