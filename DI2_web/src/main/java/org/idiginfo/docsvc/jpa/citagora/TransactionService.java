package org.idiginfo.docsvc.jpa.citagora;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

	public static final String CITAGORA_OBJ_CLASS_NAME = CitagoraObjectImpl.class
			.getCanonicalName();
	
	public static final String REFERENCE_CLASS_NAME = ReferenceImpl.class
			.getCanonicalName();
	
	
	@PersistenceContext
	private EntityManager entityManager ;
	
	@Transactional
	public boolean modify(MultivaluedMap<String, String> queryParams){
		Query q = entityManager.createQuery("SELECT e FROM " + REFERENCE_CLASS_NAME
				+ " e WHERE e.uri=:uri");
		q.setParameter("uri", queryParams.getFirst("uri"));
		List<Reference> references = q.getResultList();
		if(references.size()>0){
			Reference ref = references.get(0);
			ref.setSource(queryParams.getFirst("source"));
			ref.setLanguage(queryParams.getFirst("language"));
			ref.setTitle(queryParams.getFirst("title"));
			ref.setSubject(queryParams.getFirst("subject"));
			ref.setShortTitle("Short Article");
			ref.setDoi(queryParams.getFirst("doi"));
			ref.setPmid(queryParams.getFirst("pmid"));
			entityManager.flush();
			return true;
		}
		return false;
	}
	
	@Transactional
	public void createContainer(MultivaluedMap<String, String> queryParams){
		ContainerImpl document = new ContainerImpl();
		document.setSource(queryParams.getFirst("source"));
		document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
		// first review
		ReviewImpl review = new ReviewImpl();
		review.setDocumentReviewed(document);
		review.setRatingType(RatingType.getUri("overall"));
		review.setRating(4);
		PersonImpl person = new PersonImpl();
		review.setReviewer(person);
		person.setAccountName("a_user_foaf");
		// second review
		review = new ReviewImpl();
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(50);
		review.setRating(4);
		// third review
		review = new ReviewImpl();
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(10);
		review.setRating(3);

		// tag
		TagImpl tag = new TagImpl();
		tag.setTarget(document);
		AnnotationBodyImpl body = (AnnotationBodyImpl) tag.getBody();
		body.setChars("actual tag");
		body.setCharacterEncoding("UTF-8");
		PersonImpl annotator = new PersonImpl();
		annotator.setAccountName("registered_annotator");
		tag.setAnnotator(annotator);
		tag.setAnnotated(new GregorianCalendar(2012, 6, 12).getTime());
		PersonImpl generator = (PersonImpl) PersonImpl.createCitagoraAgent();
		generator.setAccountName("http://citagora.com/annotator");
		tag.setGenerator(generator);
		tag.setGenerated(new GregorianCalendar(2012, 6, 12).getTime());
		tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

		// Reference
		ReferenceImpl reference = new ReferenceImpl();
		document.setIsAbout(reference);
//		reference.setLanguage("English");
		reference.setLanguage(queryParams.getFirst("language"));
		reference.addContainer(document);
		reference
				.addSeeAlso("another link that also provides some information about this article");
//		reference.setUri("http://example.com/article/1");
		reference.setUri(queryParams.getFirst("uri"));

//		reference.setTitle("Some Journal Article");
		reference.setTitle(queryParams.getFirst("title"));

//		reference.setSubject("some keyword");
		reference.setSubject(queryParams.getFirst("subject"));


		reference.setShortTitle("Short Article");
		reference
				.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
//		reference.setDoi("doi id");
		reference.setDoi(queryParams.getFirst("doi"));

		// reference.setId("doi:doi id");
//		reference.setPmid("pmid number");
		reference.setPmid(queryParams.getFirst("pmid"));

		// note identifier is multi-valued
		// <dcterms:identifier>pmid:pmid number</dcterms:identifier>

		reference.setPublisher("a publisher as plain text");
		reference.setVolume("6");
		reference.setPageStart(8);
		reference.setPageEnd(8);
		reference.setPages("234 - 343");
	
		entityManager.persist(document);
		
		entityManager.flush();
	}
	
}
