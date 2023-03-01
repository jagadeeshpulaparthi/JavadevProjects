package com.arka.module.cms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.entity.ClientMaster;
import com.arka.module.cms.entity.DivisionMaster;
import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.NestedFolder;
import com.arka.module.cms.entity.SubFolder;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.model.DTO.TenantOnboard;
import com.arka.module.cms.repo.ClientMasterRepo;
import com.arka.module.cms.repo.DivisionMasterRepo;
import com.arka.module.cms.repo.FolderRepo;
import com.arka.module.cms.repo.NestedFolderRepo;
import com.arka.module.cms.repo.SubFolderRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.utils.Response;

@Service
public class ClientMasterService {
	private  Logger log = LoggerFactory.getLogger(ClientMasterService.class);


	@Autowired
	ClientMasterRepo cmRepo;
	Response response ;

	@Autowired
	UserMasterRepo usersRepo;

	@Autowired
	DivisionMasterRepo dmRepo;

	@Autowired
	FolderRepo fRepo;

	@Autowired
	SubFolderRepo sfRepo;

	@Autowired
	NestedFolderRepo nfRepo;



	public Object  createCompany(@RequestBody ClientMaster client) {

		ClientMaster addClient = cmRepo.save(client);
		if(addClient != null) {
			return addClient;
		}else {
			response = new Response (204,"Please try again",null);
			return response;
		}

	}

	public Object getCompanies() {
		List<ClientMaster> findAll = cmRepo.findByMarkForDeletion(0);
		if(findAll.isEmpty()) {
			response = new Response (204,"Please try again",null);
			return response;
		}else {
			return findAll;
		}
	}
	public Object deleteCompany(Long id ) {
		List<ClientMaster> findById = cmRepo.findByClientId(id);
		if(findById != null ) {
			ClientMaster div = findById.get(0);
			div.setMarkForDeletion(1);
			ClientMaster save = cmRepo.save(div);
			response = new Response(200, "Deleted Sucessfully");
		}else {
			response = new Response(204, "Failed to delete");
		}
		return response;
	}

	public Object updateCompany(Long id,String address,String modifiedBy,String name,String city,String pincode) {
		List<ClientMaster> findById = cmRepo.findByClientId(id);

		ClientMaster  addDiv = findById.get(0);
		addDiv.setAddress(address);
		addDiv.setCity(city);
		addDiv.setPincode(pincode);
		addDiv.setName(name);
		addDiv.setModifiedBy(modifiedBy);
		ClientMaster save = cmRepo.save(addDiv);
		if(save != null) {
			return save;		
		}else {
			response = new Response (204,"Failed to Update");
			return response;
		}

	}

	public Object onBoardTenant(@RequestBody TenantOnboard tenant ) {
		String organizationId = (tenant.getTenantID() + "_" +tenant.getDivisionName()).toUpperCase();
		log.info("Created User........");
//		UserMaster findByEmail = usersRepo.findByEmailId(tenant.getTenantEmail());
		ClientMaster clientList = cmRepo.findByNameAndCreatedBy(tenant.getTenantID(), tenant.getTenantEmail());
		if(clientList == null) {
		ClientMaster addClient = new ClientMaster();
		addClient.setName(tenant.getTenantID());
		addClient.setCreatedBy(tenant.getTenantEmail());
		ClientMaster client = cmRepo.save(addClient);

		if(client != null) {
			log.info("Created division........");
			DivisionMaster  addDiv =  new DivisionMaster();
			addDiv.setClientId(tenant.getTenantID());
			addDiv.setDivisionName(tenant.getDivisionName());
			addDiv.setCreatedBy(tenant.getTenantEmail());
			addDiv.setOrganizationId(organizationId);
			DivisionMaster div = dmRepo.save(addDiv);
			if(div != null) {
				log.info("Created user........");

				UserMaster userM = new UserMaster();
				userM.setEmailId(tenant.getTenantEmail());
				userM.setOrganizationId(organizationId);
				userM.setPassword(tenant.getPassword());
				userM.setRoleId(2);				
				UserMaster save = usersRepo.save(userM);

				if(save != null) {
					log.info("Created Folder........");
					String folName = "TENANT" +"_" +tenant.getTenantID();
					UserMaster findByEmailId = usersRepo.findByEmailIdAndOrganizationId(tenant.getTenantEmail(),organizationId);
					Long userId =  findByEmailId.getUserId();
					System.out.print("userId"+ userId);
					Folder createFolder = new Folder();
					createFolder.setName(folName);
					createFolder.setOrganizationId(organizationId);
					createFolder.setCreatedBy(tenant.getTenantEmail());
					createFolder.setUser(findByEmailId);
					Folder saveFolder = fRepo.save(createFolder);					
					if(saveFolder != null) {
						response = new Response (200,"Success",saveFolder);
						return response;	
					}else {
						response = new Response (204,"Failed to Create Folder");
						return response;
					}
				}

			}else {
				response = new Response (204,"Failed to Generate OrganizationID");
				return response;	
			}
		}else {
			response = new Response (204,"Failed to Onboard Tenant");
			return response;		
		}
		}else {
			response = new Response (204,"Tenant already exists");
			return response;	
		}
		return response;
	}


	public Object onBoardStore(@RequestBody TenantOnboard tenant) {
		log.info("Creating Store........");	

		String organizationId = (tenant.getTenantID() + "_" +tenant.getDivisionName()).toUpperCase();
		String store = ("STORE"+"_"+tenant.getStore()).toUpperCase();
		String tenant1 = ("TENANT"+"_"+tenant.getTenantID()).toUpperCase();
		UserMaster findByEmailId = usersRepo.findByEmailIdAndOrganizationId(tenant.getTenantEmail(),organizationId);
//		if(findByEmailId ==  null) {
//			UserMaster userM = new UserMaster();
//			userM.setEmailId(tenant.getTenantEmail());
//			userM.setOrganizationId(organizationId);
//			UserMaster save = usersRepo.save(userM);
//		}
		Long userId =  findByEmailId.getUserId();
		Folder folder = fRepo.findByUserIdAndOrganizationIdAndNameAllIgnoreCase(userId, organizationId,tenant1);
		Long fID = folder.getFolderId();
		if(folder == null) {
			response = new Response (204,"No Tenant folder found for OrganizationID");
			return response;
		}else {
			SubFolder subfol = sfRepo.findBySubFolderNameAndOrganizationIdAllIgnoreCase(store,organizationId);
			System.out.println("subfol"+ subfol);
			if (subfol == null) {
			SubFolder subFolder = new SubFolder();
			subFolder.setSubFolderName(store);
			subFolder.setFolderId(fID);
			subFolder.setCreatedBy(tenant.getTenantEmail());
			subFolder.setMarkForDeletion(0);
			subFolder.setOrganizationId(organizationId);
			subFolder.setUserId(userId);
			SubFolder save = sfRepo.save(subFolder);
			if(save != null) {
				SubFolder subfolder = sfRepo.findBySubFolderNameAndOrganizationIdAllIgnoreCase(store, organizationId);
				Long sfID = subfolder.getSubFolderId();
				NestedFolder nFolder = new NestedFolder();
				nFolder.setOrganizationId(organizationId);
				nFolder.setSubFolderId(sfID);
				nFolder.setFolderId(fID);
				nFolder.setCreatedBy(tenant.getTenantEmail());
				nFolder.setUserId(userId);
				nFolder.setMarkForDeletion(0);
				nFolder.setNestFolderName("Carousel");
				nfRepo.save(nFolder);

				NestedFolder nFolder1 = new NestedFolder();
				nFolder1.setOrganizationId(organizationId);
				nFolder1.setSubFolderId(sfID);
				nFolder1.setFolderId(fID);
				nFolder1.setCreatedBy(tenant.getTenantEmail());
				nFolder1.setUserId(userId);
				nFolder1.setMarkForDeletion(0);
				nFolder1.setNestFolderName("Header");
				nfRepo.save(nFolder1);

				NestedFolder nFolder2 = new NestedFolder();
				nFolder2.setOrganizationId(organizationId);
				nFolder2.setSubFolderId(sfID);
				nFolder2.setFolderId(fID);
				nFolder2.setCreatedBy(tenant.getTenantEmail());
				nFolder2.setUserId(userId);
				nFolder2.setMarkForDeletion(0);
				nFolder2.setNestFolderName("Footer");
				nfRepo.save(nFolder2);

				NestedFolder nFolder3 = new NestedFolder();
				nFolder3.setOrganizationId(organizationId);
				nFolder3.setSubFolderId(sfID);
				nFolder3.setFolderId(fID);
				nFolder3.setCreatedBy(tenant.getTenantEmail());
				nFolder3.setUserId(userId);
				nFolder3.setMarkForDeletion(0); 
				nFolder3.setNestFolderName("Container");
				nfRepo.save(nFolder3);
				response = new Response (200,"Success",nFolder);
				return response;
			}else
				response = new Response (204,"Failed to create Nestedfolder");
				return response;
		}

		
		else {
			response = new Response (204,"Folder is already created for store::"+ tenant.getStore());
			return response;
		}
	}

	}

}
