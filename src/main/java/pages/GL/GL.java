package pages.GL;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GL extends Utilities {
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    String newAcCode = "";
    String xmlFilePath = System.getProperty("user.dir") + "/src/test/resources/UploadData/GL44_CreateModifyACCode.xml";
    @FindBy(id = "LinkProduct0")
    private WebElement accounting;
    @FindBy(id = "divmodule_5")
    private WebElement generalLedger;
    @FindBy(xpath = "//a[@title=\"GL44 Create/Modify Account Code\"]")
    private WebElement createModifyAccCode;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement add;
    @FindBy(id = "sortimage0")
    private WebElement sortImage;
    @FindBy(id = "caption_account_code")
    private WebElement captionaccCode;
    @FindBy(id = "td_0_0")
    private WebElement getAccCode;
    @FindBy(id = "cphBody_txtAccCode")
    private WebElement accCode;
    @FindBy(id = "cphBody_txtAccName")
    private WebElement shortDesc;
    @FindBy(id = "cphBody_txtLongDescription")
    private WebElement longDesc;
    @FindBy(id = "cphBody_imggroup_id")
    private WebElement accGroup;
    @FindBy(id = "cphBody_txtgroup_id")
    private WebElement searchAccGr;
    @FindBy(id = "td_0_1")
    private WebElement firstCol;
    @FindBy(id = "cphBody_chkAP")
    private WebElement chkAP;
    @FindBy(id = "cphBody_chkAR")
    private WebElement chkAR;
    @FindBy(id = "cphBody_chkGC")
    private WebElement chkGC;
    @FindBy(id = "cphBody_chkIV")
    private WebElement chkInv;
    @FindBy(id = "cphBody_chkIF")
    private WebElement chkIF;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;
    @FindBy(id = "cphBody_ddlAccountCategory")
    private WebElement accountCategory;
    @FindBy(id = "cphBody_rdoAssets")
    private WebElement assets;
    @FindBy(id = "cphBody_rdoLiabilities")
    private WebElement liabilities;
    @FindBy(id = "cphBody_rdoIncome")
    private WebElement income;
    @FindBy(id = "cphBody_rdoExpense")
    private WebElement expense;
    @FindBy(id = "cphBody_chkPrePaidAccCode")
    private WebElement prePaidAccount;
    @FindBy(id = "cphBody_chkDirectPostingNotallow")
    private WebElement directPostingNotAllow;
    @FindBy(id = "cphBody_chkgst_flag")
    private WebElement isTaxAccount;
    @FindBy(id = "btnOK")
    private WebElement okButton;
    @FindBy(xpath = "//a[text()='Map Account with Department']")
    private WebElement mapAccountcode;
    @FindBy(id = "rdoAll")
    private WebElement all;
    @FindBy(id = "col_0")
    private WebElement searchAccCode;
    @FindBy(xpath = "//*[starts-with(@id, 'sel_flag_7_')]")
    private List<WebElement> flagElements;
    @FindBy(id = "cphBody_lblMap")
    private WebElement mappingwithdept;
    @FindBy(id = "FileDlg")
    private WebElement choseFile;
    @FindBy(id = "btnUpLoadData")
    private WebElement uploadData;
    @FindBy(xpath = "//a[@title='GL31 Exchange Rate Setup']")
    private WebElement exchangeRateSetup;

    @FindBy(id = "td_0_1")
    private WebElement td01;
    @FindBy(id = "Control_1_0")
    private WebElement control10;


    @FindBy(id = "cphBody_imgPopupFxCurrencyCode")
    private WebElement currencyCode;



    @FindBy(id = "td_1_1")
    private WebElement td11;
    @FindBy(xpath = "//*[@id=\"Control_1_0\"]")
    private List<WebElement> rateSetup;
    @FindBy(xpath = "//input[starts-with(@id, 'Control_1_1')]")
    private List<WebElement> controlList;
    @FindBy(xpath = "//input[@name='amount_fx']")
    private WebElement fxAmount;
    @FindBy(id = "cphBody_rdoShowType_2")
    private WebElement bothAbove;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement searchButton;
    @FindBy(id = "Control_1_2")
    private WebElement amtLocal;
    @FindBy(id = "cphBody_txttrans_type")
    private WebElement jvType;
    @FindBy(id = "acct_code_2_0")
    private WebElement acctCode20;
    @FindBy(xpath = "//*[starts-with(@id, 'acct_code_2_')]")
    private List<WebElement> acctCodeList;
    @FindBy(xpath = "//*[starts-with(@id, 'amount_8_')]")
    private List<WebElement> amountList;
    @FindBy(xpath = "//*[starts-with(@id, 'particulars_12_')]")
    private List<WebElement> narrationList;
    @FindBy(id = "amount_8_0")
    private WebElement amount80;
    @FindBy(id = "particulars_12_0")
    private WebElement narration;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveandClose;
    @FindBy(xpath = "//a[@title='GL05 Create Journal Voucher']")
    private WebElement createAC;
    @FindBy(id = "cphBody_btnAddRow_Top")
    private WebElement addRow;
    @FindBy(id = "dr_cr_flg_9_1")
    private WebElement drCrFlg;
    @FindBy(id = "col_1")
    private WebElement SearchAccCode;
    @FindBy(id = "cphBody_rdoAll")
    private WebElement rdoAll;
    @FindBy(id = "chkFreeze")
    private WebElement freeze;
    @FindBy(xpath = "//*[@title='GL17 Post Interface Transactions']")
    private WebElement postInterface;
    @FindBy(id = "cphBody_lstTransDates")
    private WebElement selectDates;

    @FindBy(xpath = "//input[@name='amount_local']")
    private List<WebElement> amountLocal;

    @FindBy(xpath = "//input[@name='amount_fx']")
    private List<WebElement> forexAmount;

    public GL(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void updateLastAccountCode(String xmlPath, String newAcCode) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(xmlPath));

            Namespace ss = Namespace.getNamespace("ss", "urn:schemas-microsoft-com:office:spreadsheet");
            Element workbook = doc.getRootElement();

            Element worksheet = workbook.getChildren("Worksheet", ss).stream().filter(ws -> "Data".equalsIgnoreCase(ws.getAttributeValue("Name", ss))).findFirst().orElseThrow(() -> new RuntimeException("Worksheet 'Data' not found"));

            Element table = worksheet.getChild("Table", ss);

            List<Element> rows = table.getChildren("Row", ss);

            if (rows.size() < 2) {
                throw new RuntimeException("No data rows found (only header exists).");
            }

            // Extract prefix and numeric part
            String prefix = newAcCode.replaceAll("(\\d+)$", "");
            String numberPart = newAcCode.replaceAll("^.*?(\\d+)$", "$1");
            int baseNum = Integer.parseInt(numberPart);

            // Loop top-down through all data rows (skip header row)
            int counter = 0;

            for (int i = 1; i < rows.size(); i++) {

                Element row = rows.get(i);
                List<Element> cells = row.getChildren("Cell", ss);
                if (cells.size() < 4) continue;

                Element accCell = cells.get(3);
                Element data = accCell.getChild("Data", ss);
                if (data == null) continue;

                int currentVal = baseNum + counter;
                String formatted = prefix + String.format("%0" + numberPart.length() + "d", currentVal);

                data.setText(formatted);
                counter++;
            }

            // Save file WITHOUT breaking Excel formatting
            Format format = Format.getRawFormat().setOmitDeclaration(false).setOmitEncoding(false);
            XMLOutputter out = new XMLOutputter(format);
            out.output(doc, new FileOutputStream(xmlPath));

        } catch (Exception e) {
            throw new RuntimeException("Error updating codes in top-down order", e);
        }
    }

    public static void updateLastAccountCode1(String xmlPath, String newAccCode) {
        try {
            // Load entire XML as plain text (preserves formatting)
            String xml = new String(Files.readAllBytes(Paths.get(xmlPath)));

            // Regex to locate 4th <Cell> <Data> in last <Row>
            // This handles values like: 2676B4, 34100A, 14100A etc.
            Pattern pattern = Pattern.compile("(?s)(<Row[^>]*>\\s*(?:<Cell[^>]*>.*?</Cell>\\s*){3}<Cell[^>]*>\\s*<Data[^>]*>)(.*?)(</Data>)");

            Matcher matcher = pattern.matcher(xml);

            String lastMatch = null;
            while (matcher.find()) {
                lastMatch = matcher.group(0); // we only care about the last row's match
            }

            if (lastMatch == null) {
                throw new RuntimeException("Could not find Account Code cell in XML.");
            }

            // Replace account code only inside last match
            String updated = matcher.replaceFirst("$1" + newAccCode + "$3");

            // Write back file WITHOUT modifying structure
            Files.write(Paths.get(xmlPath), updated.getBytes());

        } catch (Exception e) {
            throw new RuntimeException("Error updating XML file safely", e);
        }
    }

    public void multipageiframebrw() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
    }

    public void gl() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            multipageiframebrw();
            Click(accounting);
            multipageiframebrw();
            wait.until(ExpectedConditions.elementToBeClickable(generalLedger));
            Click(generalLedger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createAccCode(String type, boolean isTaxAcc, boolean prePaidAcc, boolean directPosting) {
        try {


            Click(createModifyAccCode);
            multipageiframebrw();
            if (type.equalsIgnoreCase("assets")) {
                Click(assets);
            } else if (type.equalsIgnoreCase("liabilities")) {
                Click(liabilities);
            } else if (type.equalsIgnoreCase("income")) {
                Click(income);
            } else if (type.equalsIgnoreCase("expense")) {
                Click(expense);
            }
            Click(captionaccCode);
            Click(sortImage);
            String alt = sortImage.getAttribute("alt");
            if (alt == "ASC") {
                Click(sortImage);
            }

            String lastAcCode = getAccCode.getText();

            String prefix = lastAcCode.replaceAll("(\\d+)$", "");
            String numberPart = lastAcCode.replaceAll("^.*?(\\d+)$", "$1");
            int incremented = Integer.parseInt(numberPart) + 1;
            String newNumberPart = String.format("%0" + numberPart.length() + "d", incremented);
            newAcCode = prefix + newNumberPart;
            System.out.println("Account Code :" + newAcCode);
            Click(add);
            SendKeys(accCode, newAcCode);
            SendKeys(shortDesc, "Short Description");
            SendKeys(longDesc, "Long Description");
//            Click(accGroup);
//            iframeGridDialog();

            if (isTaxAcc) {
                Click(isTaxAccount);
            }
            if (prePaidAcc) {
                Click(prePaidAccount);
            }
            if (directPosting) {
                Click(directPostingNotAllow);
            }

            if (type.equalsIgnoreCase("assets")) {
                SendKeys(searchAccGr, "Assets");
                searchAccGr.sendKeys(Keys.TAB);
            } else if (type.equalsIgnoreCase("liabilities")) {
                SendKeys(searchAccGr, "Liabilities");
                searchAccGr.sendKeys(Keys.TAB);

            } else if (type.equalsIgnoreCase("income")) {
                SendKeys(searchAccGr, "Income");
                searchAccGr.sendKeys(Keys.TAB);

            } else if (type.equalsIgnoreCase("expense")) {
                SendKeys(searchAccGr, "Expense");
                searchAccGr.sendKeys(Keys.TAB);

            }


            iframeGridDialog();
            DoubleClick(firstCol);
            multipageiframebrw();
            Click(chkAP);
            Click(chkAR);
            Click(chkIF);
            Click(chkGC);
            Click(chkInv);
            Click(saveButton);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create Acc Code", Status.FAIL, "Issue in Creating Account Code", true, e);
        }
    }

    public void iframeGridDialog() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
    }

    public void mapAccCode() {
        try {
            iframeGridDialog();
            Click(okButton);
            multipageiframebrw();
            Click(generalLedger);
            Click(mapAccountcode);
            //BaseTest.getDriver().switchTo().frame("iframeDtl");
            multipageiframebrw();
            try {
                BaseTest.getDriver().switchTo().frame("iframeDtl");
                System.out.println("Inside the frame");
            } catch (Exception e) {
                System.out.println("Outside frame");
            }

            Click(all);
            SendKeys(searchAccCode, newAcCode);
            searchAccCode.sendKeys(Keys.TAB);

            multipageiframebrw();

            int clickCount = 0;
            for (WebElement flagElement : flagElements) {
                if (flagElement.isDisplayed() && flagElement.isEnabled() && clickCount < 3) {
                    flagElement.click();
                    clickCount++;
                }
            }

            Click(saveButton);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Map Account Code", Status.FAIL, "Issue in Maping the Account Code with GL", true, e);
        }
    }

    public void mapAccountCodeScreen() {
        try {
            iframeGridDialog();
            Click(okButton);
            multipageiframebrw();
            Click(mappingwithdept);

            BaseTest.getDriver().switchTo().defaultContent();

            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            Click(all);

            SendKeys(searchAccCode, newAcCode);
            searchAccCode.sendKeys(Keys.TAB);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            int clickCount = 0;
            for (WebElement flagElement : flagElements) {
                if (flagElement.isDisplayed() && flagElement.isEnabled() && clickCount < 3) {
                    flagElement.click();
                    clickCount++;
                }
            }

            Click(saveButton);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Map Account Code", Status.FAIL, "Issue in Maping the Account Code with GL", true, e);
        }
    }

    public void xmlUpload() {
        try {
            Click(createModifyAccCode);
            multipageiframebrw();
            //            if (type.equalsIgnoreCase("assets")) {
//                Click(assets);
//            } else if (type.equalsIgnoreCase("liabilities")) {
//                Click(liabilities);
//            } else if (type.equalsIgnoreCase("income")) {
//                Click(income);
//            } else if (type.equalsIgnoreCase("expense")) {
//                Click(expense);
//            }
            Click(captionaccCode);
            Click(sortImage);
            String alt = sortImage.getAttribute("alt");
            if (alt == "ASC") {
                Click(sortImage);
            }
            String lastAcCode = getAccCode.getText();

            String prefix = lastAcCode.replaceAll("(\\d+)$", "");
            String numberPart = lastAcCode.replaceAll("^.*?(\\d+)$", "$1");
            int incremented = Integer.parseInt(numberPart) + 1;
            String newNumberPart = String.format("%0" + numberPart.length() + "d", incremented);
            newAcCode = prefix + newNumberPart;

            System.out.println("New account code is " + newAcCode);

            updateLastAccountCode(xmlFilePath, newAcCode);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            SendKeys(choseFile, xmlFilePath);
            Click(uploadData);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "cphBody_txtDateFrom")
    private WebElement date;
    public void exchangeRate(boolean multiDate) {
        try {
            Click(exchangeRateSetup);
            multipageiframebrw();

            if (multiDate) {
                String currentDate = date.getAttribute("value");
                System.out.println("Current Date is :" + currentDate);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate parsedDate = LocalDate.parse(currentDate, formatter);
                LocalDate updatedDate = parsedDate.minusDays(5);
                String newDate = updatedDate.format(formatter);
                System.out.println("New date is :" + newDate);
                date.sendKeys(Keys.CONTROL+"A",Keys.DELETE);
                SendKeys(date,newDate);
            }



            Click(currencyCode);
            iframeGridDialog();
            DoubleClick(td11);
            multipageiframebrw();
            Click(bothAbove);
            Click(searchButton);
            //Click(fxAmount);



            for(WebElement element : forexAmount){
                element.sendKeys(Keys.CONTROL+"A",Keys.DELETE);
                SendKeys(element, "1");
            }

            for(WebElement element : amountLocal){
                element.sendKeys(Keys.CONTROL+"A",Keys.DELETE);
                SendKeys(element, "50");
            }






//            fxAmount.sendKeys(Keys.CONTROL+"A",Keys.DELETE);
//            SendKeys(fxAmount, "1");
//            //Click(amtLocal);
//            amtLocal.sendKeys(Keys.CONTROL+"A",Keys.DELETE);
//
//            SendKeys(amtLocal, "50");

            Click(saveButton);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "cphBody_txtreverse_date")
    private WebElement reversalDate;
    @FindBy(id = "cphBody_txttrans_date")
    private WebElement date1;


    public void createJV(String type, boolean reversal) {
        try {
            Click(createAC);
            multipageiframebrw();
            SendKeys(jvType, "BV");
            //multipageiframebrw();
            SendKeys(acctCodeList.get(0), "%%");
            acctCodeList.get(0).sendKeys(Keys.TAB);

            String revdate = date1.getAttribute("value");
            System.out.println("Today Date is :"+revdate);


            if (reversal){
                wait.until(ExpectedConditions.visibilityOf(reversalDate));

                SendKeys(reversalDate,revdate);
            }

            iframeGridDialog();
            DoubleClick(firstCol);
            iframeGridDialog();
            DoubleClick(firstCol);
            multipageiframebrw();
            amountList.get(0).sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
            SendKeys(amountList.get(0), "100");
            SendKeys(narrationList.get(0), "Testing");
            Click(addRow);
            SendKeys(acctCodeList.get(0), "%%");
            acctCodeList.get(0).sendKeys(Keys.TAB);

            iframeGridDialog();
            DoubleClick(firstCol);
            iframeGridDialog();
            DoubleClick(firstCol);
            multipageiframebrw();
            amountList.get(0).sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
            SendKeys(amountList.get(0), "100");
            SendKeys(narrationList.get(0), "Testing");
            Click(drCrFlg);
            Click(saveandClose);
            Click(saveButton);
            BaseTest.getDriver().switchTo().alert().dismiss();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyAcCode() {
        try {
            iframeGridDialog();
            Click(okButton);
            multipageiframebrw();
            Click(rdoAll);
            SendKeys(SearchAccCode, newAcCode);
            SearchAccCode.sendKeys(Keys.TAB);
            SendKeys(shortDesc, "Modified Short Desc");
            SendKeys(longDesc, "Modified Long Desc");
            Click(saveButton);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void freeze() {
        try {
            Click(createModifyAccCode);
            multipageiframebrw();

            Click(captionaccCode);
            Click(sortImage);
            String alt = sortImage.getAttribute("alt");
            if (alt == "ASC") {
                Click(sortImage);
            }

            Click(freeze);
            Click(saveButton);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void postInterfaceTransection() {
        try {
            multipageiframebrw();
            Click(postInterface);
            multipageiframebrw();
            selectBy("index", selectDates, "0");

            String transDate = Utilities.selectByAndGetValue("index", selectDates, "0");
            System.out.println("Selected option is :" + selectDates);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(xpath = "//a[@title='GL08 Create/Modify Budget']")
    private WebElement createModifyBudget;
    public void createBudget(){
        try {
            Click(createModifyBudget);
            multipageiframebrw();


            // Switch to correct iframe
          //  driver.switchTo().frame("MultiPageiframeBrw");

            String inputDate = "02 December 2025";

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            DateTimeFormatter tableFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate targetDate = LocalDate.parse(inputDate, inputFormatter);

            // All start date fields
            List<WebElement> startDateElements = BaseTest.getDriver().findElements(
                    By.xpath("//input[starts-with(@id,'date_start_1_')]"));

            for (WebElement startDateEl : startDateElements) {

                String startDateValue = startDateEl.getAttribute("value").trim();

                // Extract last numeric index (date_start_1_6 → 6)
                String rowIndex = startDateEl.getAttribute("id")
                        .substring(startDateEl.getAttribute("id").lastIndexOf("_") + 1);

                WebElement endDateEl = BaseTest.getDriver().findElement(
                        By.id("date_end_2_" + rowIndex));

                String endDateValue = endDateEl.getAttribute("value").trim();

                LocalDate startDate = LocalDate.parse(startDateValue, tableFormatter);
                LocalDate endDate   = LocalDate.parse(endDateValue, tableFormatter);

                if ((targetDate.isEqual(startDate) || targetDate.isAfter(startDate)) &&
                        (targetDate.isEqual(endDate)   || targetDate.isBefore(endDate))) {

                    // 👉 Correct budget field using same row index
                    WebElement budgetInput = BaseTest.getDriver().findElement(
                            By.id("ws_amount_3_" + rowIndex));

                    budgetInput.sendKeys(Keys.CONTROL+"A");
                    budgetInput.sendKeys("50000");

                    WebElement targetInput = BaseTest.getDriver().findElement(By.id("ws_target_amt_5_" + rowIndex));
                    targetInput.sendKeys(Keys.CONTROL+"A");
                    targetInput.sendKeys("40000");

                    WebElement forcastInput = BaseTest.getDriver().findElement(By.id("ws_forecast_amt_7_" + rowIndex));
                    forcastInput.sendKeys(Keys.CONTROL+"A");
                    forcastInput.sendKeys("45000");

                    WebElement budget = BaseTest.getDriver().findElement(By.id("ws_d_c_4_" + rowIndex));
                    WebElement target = BaseTest.getDriver().findElement(By.id("ws_target_dc_6_" + rowIndex));
                    WebElement forcast = BaseTest.getDriver().findElement(By.id("ws_forecast_dc_8_" + rowIndex));

                    Click(budget);
                    Click(target);
                    Click(forcast);


                    break; // stop after match
                }}

            Click(saveButton);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "cphBody_rdoAccountGroup")
    private WebElement accountGroup;

    public void accountXML(){
        try {
            Click(createModifyBudget);
            multipageiframebrw();
            Click(accountGroup);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FindBy(xpath = "//a[@title='GL09 Create/Modify Statistics']")
    private WebElement createStat;


    @FindBy(id = "cphBody_imgPopupap_StatCode")
    private WebElement statCode;

    @FindBy(xpath = "//*[starts-with(@id,'actual_this_month_2_')]")
    private List<WebElement> periods;

    @FindBy(xpath = "//*[starts-with(@id,'actual_ytd_3_')]")
    private List<WebElement> years;




    public void createStat(){
        try {

            int[] values = {5, 10, 15, 20, 25};
            int[] Years = {5, 5, 10, 20, 40};

            Click(createStat);
            multipageiframebrw();
            Click(statCode);
            iframeGridDialog();
            DoubleClick(firstCol);
            multipageiframebrw();
            Click(searchButton);

            int count = 0;


            for (WebElement element : periods) {

                if (element.isDisplayed() && element.isEnabled()) {

                    if (count >= values.length) {
                        break;   // safety guard (should never exceed 5)
                    }



                    element.sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
                    SendKeys(element, String.valueOf(values[count]));
                    count++;
                }
            }


            int counts = 0;
            for (WebElement element : years) {



                    if (counts >= Years.length) {
                        break;   // safety guard (should never exceed 5)
                    }



                    element.sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
                    SendKeys(element, String.valueOf(Years[counts]));
                    counts++;

            }

            Click(saveButton);






        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create Stat",Status.FAIL,"Issue while creating Statistics",true,e);
        }
    }


}
