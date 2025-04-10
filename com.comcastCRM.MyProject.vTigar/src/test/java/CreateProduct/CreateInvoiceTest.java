package CreateProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.BaseClassUtility.BaseClass;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.comcast.crm.objectrepositoryUtility.AddCustPopUp;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewInvoice;
import com.comcast.crm.objectrepositoryUtility.CreatingNewProductPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewSalesOrderPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.InvoicePage;
import com.comcast.crm.objectrepositoryUtility.MoreLinkPopUp;
import com.comcast.crm.objectrepositoryUtility.OrganisationInfoPAge;
import com.comcast.crm.objectrepositoryUtility.OrganisationPage;
import com.comcast.crm.objectrepositoryUtility.ProductPage;
import com.comcast.crm.objectrepositoryUtility.SalesOrderPage;
import com.comcast.crm.objectrepositoryUtility.addOrgnisationPopUp;
import com.comcast.crm.objectrepositoryUtility.creatingNewOrganisationPage;

@Listeners(com.comcast.crm.listenerUtility.ListenerImplementationClass.class)

public class CreateInvoiceTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createInvoice() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "Read the Data from Excel");
		String orgname = elib.getDatafromExcel("org", 1, 2) + jlib.getRandomNumber();
		String firstname = elib.getDatafromExcel("Contact", 1, 2) + jlib.getRandomNumber();
		String lastname = elib.getDatafromExcel("Contact", 1, 3) + jlib.getRandomNumber();
		String product = elib.getDatafromExcel("Product", 1, 2) + jlib.getRandomNumber();
		String productcategory = elib.getDatafromExcel("Product", 1, 3);
		String productqty = elib.getDatafromExcel("Product", 1, 5);
		String productprice = elib.getDatafromExcel("Product", 1, 4);
		String InvoiceSub = elib.getDatafromExcel("Invoice", 1, 2);
		String invcustNo = elib.getDatafromExcel("Invoice", 1, 1);
		String SalesSub = elib.getDatafromExcel("Sales Order", 1, 2);
		String salCustNo = elib.getDatafromExcel("Sales Order", 1, 1);
		String salCustbilladress = elib.getDatafromExcel("Sales Order", 1, 3);
		String salProQty = elib.getDatafromExcel("Sales Order", 1, 5);
		String InvCustBillAdress = elib.getDatafromExcel("Invoice", 1, 3);
		String InvProductQty = elib.getDatafromExcel("Invoice", 1, 5);
		


		UtilityClassObject.getTest().log(Status.INFO, "Navibagte to Organisation page");
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "Click on new Organisation Button");
		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrganisationBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the create new organisation");
		creatingNewOrganisationPage cnop = new creatingNewOrganisationPage(driver);
		cnop.getOraganisationNameTxtField().sendKeys(orgname);
		cnop.getSaveBtn().click();
		Thread.sleep(2000);
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Header Text ");
		OrganisationInfoPAge oip = new OrganisationInfoPAge(driver);
		String HeadName = oip.getOrgHeaderInfo().getText();
		boolean a = HeadName.contains(orgname);
		Assert.assertEquals(a, true);
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the Contact Page");
		hp.getContactLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to new contact page");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Create the Contact with firstname and lastname ");
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getFirstNameTxtField().sendKeys(firstname);
		cncp.getLastNameTxtField().sendKeys(lastname);
		cncp.getSaveBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Product Page");
		hp.getProductsLink().click();
		ProductPage pp = new ProductPage(driver);
		pp.getCreateProduct().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Product Page");
		CreatingNewProductPage cnpp = new CreatingNewProductPage(driver);
		cnpp.getProductNameTextField().sendKeys(product);
		WebElement productactive = cnpp.getProductActiveCheckBox();
		boolean pro = productactive.isSelected();
		Assert.assertEquals(pro, true);
		WebElement ele2 = cnpp.getProductCategoryDropDown();
		wlib.select(ele2, productcategory);
		String Salesstrtdate = jlib.getSystemDateYYYYDDMM();
		String salesEndDate = jlib.getRequiredDateYYYYDDMM(60);
		cnpp.getSalesStartDate().sendKeys(Salesstrtdate);
		cnpp.getSalesEndDate().sendKeys(salesEndDate);
		UtilityClassObject.getTest().log(Status.INFO, "Check the Handle user is checked");
		WebElement handlerUser = cnpp.getHandlerUserRadioBtn();
		boolean user = handlerUser.isSelected();
		Assert.assertEquals(user, true);
		cnpp.getProductPrice().sendKeys(productprice);
		cnpp.getProductStock().sendKeys(productqty);
		wlib.scrollToElementAndClick(driver, ele2);
		WebElement savebtn = cnpp.getSaveBtn();
		wlib.scrollToElementAndClick(driver, savebtn);
		savebtn.click();

		WebElement ele1 = hp.getMoreLink();
		wlib.mouseMoveOnElement(driver, ele1);
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Sales Order Page");
		MoreLinkPopUp mp = new MoreLinkPopUp(driver);
		mp.getSalesOrder().click();
		SalesOrderPage sop = new SalesOrderPage(driver);
		sop.getCreateSalesOrder().click();
		CreatingNewSalesOrderPage cnso = new CreatingNewSalesOrderPage(driver);
		cnso.getSubjectTextField().sendKeys(SalesSub);
		cnso.getCustNo().sendKeys(salCustNo);
		cnso.getAddContactName().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to add Customer Name");
		wlib.switchToWindowByURL(driver, "module=Contacts&action");
		AddCustPopUp acp = new AddCustPopUp(driver);
		acp.getCustSearchTextField().sendKeys(lastname);
		acp.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'" + lastname + "')]")).click();
		wlib.AlertPopupAccept(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Sales Order Page");
		wlib.switchToWindowByURL(driver, "module=SalesOrder&action");
		cnso.getAddOrganisation().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the Add Organisation PopUp");
		wlib.switchToWindowByURL(driver, "module=Accounts&action");
		addOrgnisationPopUp agp = new addOrgnisationPopUp(driver);
		agp.getOrgSearchTextField().sendKeys(orgname);
		agp.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		wlib.AlertPopupAccept(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Sales Order Page");
		wlib.switchToWindowByURL(driver, "module=SalesOrder&action");
		cnso.getEndPeriodDate().clear();
		String EndPeriDate = jlib.getRequiredDateYYYYDDMM(60);
		cnso.getEndPeriodDate().sendKeys(EndPeriDate);
		cnso.getBillAddressTextField().sendKeys(salCustbilladress);
		cnso.getCopyBillingAddressRadioBtn().click();
		String ShipAdress = cnso.getShipAddressTextField().getText();
		boolean address = salCustbilladress.contains(ShipAdress);
		Assert.assertEquals(address, true);
		cnso.getAddItemBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the Add Product PopUp");
		wlib.switchToWindowByURL(driver, "module=Products&action");
		cnso.getSearchProductTextField().sendKeys(product);
		cnso.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'" + product + "')]")).click();
		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Sales Order Page");
		wlib.switchToWindowByURL(driver, "module=SalesOrder&action");
		WebElement ab = cnso.getProductQuntity();
		wlib.scrollToElement(driver, ab);
		ab.sendKeys(salProQty);
		cnso.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Invoice Page");
		wlib.mouseMoveOnElement(driver, ele1);
		mp.getInvoice().click();
		InvoicePage ip = new InvoicePage(driver);
		ip.getAddInvoiceBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Creating New Invoice");
		CreatingNewInvoice cni = new CreatingNewInvoice(driver);
		cni.getSubjectTxtField().sendKeys(InvoiceSub);
		cni.getaddCustNo().sendKeys(invcustNo);
		cni.getContactAddBtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Add Contact PopUp");
		wlib.switchToWindowByURL(driver, "module=Contacts&action");
		acp.getCustSearchTextField().sendKeys(lastname);
		acp.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'" + lastname + "')]")).click();
		wlib.AlertPopupAccept(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Inoice Page");
		wlib.switchToWindowByURL(driver, "module=Invoice&action");
		cni.getDueDate().clear();
		String dueDate = jlib.getRequiredDateYYYYDDMM(90);
		cni.getDueDate().sendKeys(dueDate);
		cni.getOrganisationAddBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the Add Organisation PopUp");
		wlib.switchToWindowByURL(driver, "module=Accounts&action");
		agp.getOrgSearchTextField().sendKeys(orgname);
		agp.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		wlib.AlertPopupAccept(driver);
		
		
		
		
		
		
		
		

		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Inoice Page");
		wlib.switchToWindowByURL(driver, "module=Invoice&action");
		cni.getAssignedRadioBtn();
		WebElement Assign = cni.getAssignedRadioBtn();
		boolean assi = Assign.isSelected();
		Assert.assertEquals(assi, true);
		cni.getBillingAddressTxtField().sendKeys(InvCustBillAdress);
		cni.getCopyBillingaddressRadio().click();
		String BillAdress = cni.getBillingAddressTxtField().getText();
		boolean Billadd = BillAdress.contains(InvCustBillAdress);
		Assert.assertEquals(Billadd, true);
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the Add Product PopUp");
		wlib.switchToWindowByURL(driver, "module=Products&action");
		cnso.getSearchProductTextField().sendKeys(product);
		cnso.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'" + product + "')]")).click();
		UtilityClassObject.getTest().log(Status.INFO, "Back To Create New Inoice Page");
		wlib.switchToWindowByURL(driver, "module=Invoice&action");
		cni.getProductQty().sendKeys(InvProductQty);
		cni.getSaveBtn().click();
		
		
	}

}
