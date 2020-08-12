package CylinderSemantic.Handles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CylinderHandle {
    private int elementsNumber = 9;
    private int equationNumber = 9;
    private int [][] RTable = new int[this.elementsNumber][this.equationNumber]; // Relationship Table
    private float [][] VTable = new float[this.elementsNumber][this.equationNumber]; // Value Table
    private int stop;
    private static final float pi = (float) 3.1415926535897931;

    /**
     * Get lstBuocGiai
     * @return ArrayList<String>
     */
    public ArrayList<String> getLstBuocGiai() {
        return lstBuocGiai;
    }

    private ArrayList<String> lstBuocGiai = new ArrayList<String>();


    /**
     * Start Action Calculate
     */
    public void start(ArrayList<JTextField> txtElements,JTextField txtResult, String selectElement,JLabel lbCylinderImage){
        // Clear in ListBuocGiai
        stop = 0;
        lstBuocGiai.clear();

        // Set Flat is true
        boolean flag = true;

        // Get All Value User Type
        this.getAllElementsInGUI(txtElements);

        // Run Loop with condition is flag is true
        while(flag == true){

            // set flag is false
            flag = false;

            // Run Loop with from first equation to last equation in Program
            for (int i = 0; i < equationNumber; i++) {

                // Get element you type in this equation
                int getElement = this.getElement(i);

                // Condition if element exits
                if(getElement != -1){

                    // Condition if variable stop is 1 then stop func
                    if(stop == 1)
                        break;

                    // Run Spreading Mechanismactive with equation and element
                    activeSpreadingMechanismactive(i, getElement);

                    // Set flag is true
                    flag = true;

                    // Condition Element you want calculator have been calculated
                    if(checkElementCaculated(selectElement)){

                        // If Exits then Write Result into TextField Result
                        writeElementCaculated(selectElement, txtResult);
                        lbCylinderImage.setIcon(new ImageIcon(this.imageCylinderSemanticChange().getScaledInstance(620, 408, Image.SCALE_DEFAULT)));
                        // Set flag is false
                        flag = false;

                        // Exit function
                        break;
                    }
                }
            }
        }
    }

    /**
     * Get RTable
     * @return int [][]
     */
    public int[][] getRTable() {
        return RTable;
    }

    /**
     * Constructor TriangleHandle
     */
    public CylinderHandle() {
        initRVTable();
    }

    /**
     * Init Value for Table of Value and Table of Relationship
     *
     */
    public void initRVTable(){
        int temp = -1;
        for (int i = 0; i < elementsNumber; i++)
            for (int j = 0; j < equationNumber; j++) {
                RTable[i][j] = 0;
                VTable[i][j] = 0;
            }

        //Init Value for Table of Value
        VTable[0][1] = VTable[0][2] = VTable[0][3] = VTable[0][8] = temp;
        VTable[1][0] = VTable[1][2] = VTable[1][3] = VTable[1][4] = VTable[1][6]  = temp;
        VTable[2][4] = VTable[2][8] = temp;
        VTable[3][0] = VTable[3][1] = VTable[3][5] = temp;
        VTable[4][2] = VTable[4][7] = temp;
        VTable[5][5] = VTable[5][6] = VTable[5][7] = VTable[5][8] = temp;
        VTable[6][5] = temp;
        VTable[7][1] = VTable[7][6] = temp;
        VTable[8][3] = temp;

        //Init Value for Table of Relationship
        RTable[0][1] = RTable[0][2] = RTable[0][3] = RTable[0][8] = temp;
        RTable[1][0] = RTable[1][2] = RTable[1][3] = RTable[1][4] = RTable[1][6]  = temp;
        RTable[2][4] = RTable[2][8] = temp;
        RTable[3][0] = RTable[3][1] = RTable[3][5] = temp;
        RTable[4][2] = RTable[4][7] = temp;
        RTable[5][5] = RTable[5][6] = RTable[5][7] = RTable[5][8] = temp;
        RTable[6][5] = temp;
        RTable[7][1] = RTable[7][6] = temp;
        RTable[8][3] = temp;
    }

    /**
     * Check The Value To Be Calculated
     *
     * @param thisElement
     * @return boolean
     */
    public boolean checkElementCaculated(String thisElement){
        switch (thisElement)
        {
            case "h":
                if(VTable[0][1] == -1) {
                    break;
                }
                return true;

            case "r":
                if (VTable[1][0] == -1){
                    break;
                }
                return true;

            case "Co":
                if(VTable[2][4] == -1) {
                    break;
                }
                return true;

            case "So":
                if (VTable[3][0] == -1){
                    break;
                }
                return true;

            case "Sq":
                if(VTable[4][2] == -1) {
                    break;
                }
                return true;

            case "Sxq":
                if (VTable[5][5] == -1){
                    break;
                }
                return true;
            case "Stp":
                if(VTable[6][5] == -1) {
                    break;
                }
                return true;

            case "V":
                if (VTable[7][6] == -1){
                    break;
                }
                return true;

            case "Cq":
                if(VTable[8][3] == -1) {
                    break;
                }
                return true;
        }
        return false;
    }


    /**
     * Write The Value To Be Calculated
     *
     * @param thisElement
     *
     */
    public void writeElementCaculated(String thisElement, JTextField txtResult){
        switch (thisElement)
        {
            case "h":
                txtResult.setText(String.valueOf(VTable[0][1]));
                break;

            case "r":
                txtResult.setText(String.valueOf(VTable[1][0]));
                break;

            case "Co":
                txtResult.setText(String.valueOf(VTable[2][4]));
                break;

            case "So":
                txtResult.setText(String.valueOf(VTable[3][0]));
                break;

            case "Sq":
                txtResult.setText(String.valueOf(VTable[4][2]));
                break;

            case "Sxq":
                txtResult.setText(String.valueOf(VTable[5][5]));
                break;

            case "Stp":
                txtResult.setText(String.valueOf(VTable[6][5]));
                break;

            case "V":
                txtResult.setText(String.valueOf(VTable[7][1]));
                break;

            case "Cq":
                txtResult.setText(String.valueOf(VTable[8][3]));
                break;
        }
    }

    /**
     * Get Element in ValueTable
     *
     * @param element
     * @return int
     */
    public int getElement(int element){
        int count = 0;
        int value = -1;
        for (int i = 0; i < elementsNumber; i++)
            if(VTable[i][element] == -1){
                count++;
                value = i;
            }
        if(count == 1)
            return value;
        return -1;
    }

    /**
     * Get Value in GUI into VTable and RTable
     *
     * @param txtElements
     */
    public void getAllElementsInGUI(ArrayList<JTextField> txtElements){
        initRVTable();

        if (!this.isNullOrEmpty(txtElements.get(0).getText()))
        {
            float h = Float.parseFloat(txtElements.get(0).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[0][i] == -1f && this.VTable[0][i] != 0)
                {
                    this.VTable[0][i] = h;
                    this.RTable[0][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(1).getText()))
        {
            float r = Float.parseFloat(txtElements.get(1).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[1][i] == -1f && this.VTable[1][i] != 0)
                {
                    this.VTable[1][i] = r;
                    this.RTable[1][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(2).getText()))
        {
            float Co = Float.parseFloat(txtElements.get(2).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[2][i] == -1f && this.VTable[2][i] != 0)
                {
                    this.VTable[2][i] = Co;
                    this.RTable[2][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(3).getText()))
        {
            float So = Float.parseFloat(txtElements.get(3).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[3][i] == -1f && this.VTable[3][i] != 0)
                {
                    this.VTable[3][i] = So;
                    this.RTable[3][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(4).getText()))
        {
            float Sq = Float.parseFloat(txtElements.get(4).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[4][i] == -1f && this.VTable[4][i] != 0)
                {
                    this.VTable[4][i] = Sq;
                    this.RTable[4][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(5).getText()))
        {
            float Sxq = Float.parseFloat(txtElements.get(5).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[5][i] == -1f && this.VTable[5][i] != 0)
                {
                    this.VTable[5][i] = Sxq;
                    this.RTable[5][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(6).getText()))
        {
            float Stp = Float.parseFloat(txtElements.get(6).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[6][i] == -1f && this.VTable[6][i] != 0)
                {
                    this.VTable[6][i] = Stp;
                    this.RTable[6][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(7).getText()))
        {
            float V = Float.parseFloat(txtElements.get(7).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[7][i] == -1f && this.VTable[7][i] != 0)
                {
                    this.VTable[7][i] = V;
                    this.RTable[7][i] = 1;
                }
            }
        }




    }


    /**
     * Active Spreading Mechanismactive for Problem
     *
     * @param element
     * @param equation
     */
    public void activeSpreadingMechanismactive(int equation, int element){
        float value = -1;
        switch (equation){

            case 0:
                // CT1: So = pi*r^2
                switch (element){
                    case 1:
                        // r = Sqrt(So/pi)
                        value = (float)(Math.sqrt(VTable[3][0]/pi));
                        lstBuocGiai.add("CT1: r = Sqrt(So/π) = " + String.valueOf(value));
                        break;

                    case 3:
                        //So = pi*r^2
                        value = (float)(pi*Math.pow(VTable[1][0],2));
                        lstBuocGiai.add("CT1: So = π*r² = " + String.valueOf(value));
                        break;

                }
                break;

            case 1:
                // CT2: V = So*h
                switch (element)
                {
                    case 0:
                        // h = V/So
                        value = (float)(VTable[6][1]/VTable[3][1]);
                        lstBuocGiai.add("CT2: h = V/So = " + String.valueOf(value));
                        break;
                    case 3:
                        // So = V/h
                        value = (float)(VTable[6][1]/VTable[0][1]);
                        lstBuocGiai.add("CT2: So = V/h = " + String.valueOf(value));
                        break;
                    case 7:
                        // V = So*h
                        value = (float)(VTable[3][1]*VTable[0][1]);
                        lstBuocGiai.add("CT2: V = So*h = " + String.valueOf(value));
                        break;
                }
                break;
            case 2:
                // CT3: Sq = h*r
                switch (element)
                {
                    case 0:
                        // h = Sq/r
                        value = (float)(VTable[4][2]/VTable[0][2]);
                        lstBuocGiai.add("CT3: h = Sq/r = " + String.valueOf(value));
                        break;
                    case 1:
                        // r = Sq/h
                        value = (float)(VTable[4][2]/VTable[0][2]);
                        lstBuocGiai.add("CT3: r = Sq/h = " + String.valueOf(value));
                        break;
                    case 4:
                        // Sq = h*r
                        value = (float)(VTable[0][2]*VTable[1][2]);
                        lstBuocGiai.add("CT3: q = h*r = " + String.valueOf(value));
                        break;
                }
                break;
            case 3:
                // CT4: Cq = (h+r)*2
                switch (element)
                {
                    case 0:
                        // h = (Cq/2)-r
                        value = (float)((VTable[8][3]/2)-VTable[1][3]);
                        lstBuocGiai.add("CT4: h = (Cq/2)-r = " + String.valueOf(value));
                        break;
                    case 1:
                        // r = (Cq/2)-h
                        value = (float)((VTable[8][3]/2)-VTable[0][3]);
                        lstBuocGiai.add("CT4: r = (Cq/2)-h = " + String.valueOf(value));
                        break;
                    case 8:
                        // Cq = (h+r)*2
                        value = (float)((VTable[0][3]+VTable[1][3])*2);
                        lstBuocGiai.add("CT4: Cq = (h+r)*2 = " + String.valueOf(value));
                        break;
                }
                break;
            case 4:
                // CT5: Co = 2pi*r
                switch (element)
                {
                    case 1:
                        // r = Co/2pi
                        value = (float)(VTable[2][4]/2*pi);
                        lstBuocGiai.add("CT5: r = Co/2π = " + String.valueOf(value));
                        break;
                    case 2:
                        // Co = 2pi*r
                        value = (float)(2*pi*VTable[1][4]);
                        lstBuocGiai.add("CT5: Co = 2π*r = " + String.valueOf(value));
                        break;
                }
                break;
            case 5:
                // CT6: Stp = Sxq + 2So
                switch (element)
                {
                    case 3:
                        // So = (Stp-Sxq)/2
                        value = (float)((VTable[6][5]-VTable[5][5])/2);
                        lstBuocGiai.add("CT6: So = (Stp-Sxq)/2 = " + String.valueOf(value));
                        break;
                    case 5:
                        // Sxq = Stp - 2So
                        value = (float)(VTable[6][5] - VTable[3][5]);
                        lstBuocGiai.add("CT6: Sxq = Stp - 2So = " + String.valueOf(value));
                        break;
                    case 6:
                        // Stp = Sxq + 2So
                        value = (float)(VTable[5][5] + 2*VTable[3][5]);
                        lstBuocGiai.add("CT6: Stp = Sxq + 2So = " + String.valueOf(value));
                        break;
                }
                break;

            case 6:
                // CT7: 2V - Sxq*r = 0
                switch (element)
                {
                    case 1:
                        // r = Sxq/2V
                        value = (float)(VTable[5][6]/2*VTable[7][6]);
                        lstBuocGiai.add("CT7: r = Sxq/2V = " + String.valueOf(value));
                        break;
                    case 5:
                        // Sxq = 2V/r
                        value = (float)(2*VTable[7][6]/VTable[1][6]);
                        lstBuocGiai.add("CT7: Sxq = 2V/r = " + String.valueOf(value));
                        break;
                    case 7:
                        // V = (Sxq*r)/2
                        value = (float)((VTable[5][6]*VTable[1][6])/2);
                        lstBuocGiai.add("CT7: V = (Sxq*r)/2 = " + String.valueOf(value));
                        break;
                }
                break;
            case 7:
                // CT8: Sxq - 2pi*Sq = 0
                switch (element)
                {
                    case 4:
                        // Sq = Sxq/2pi
                        value = (float)(VTable[7][7]/2*pi);
                        lstBuocGiai.add("CT8: Sq = Sxq/2π = " + String.valueOf(value));
                        break;
                    case 5:
                        // Sxq = 2pi*Sq
                        value = (float)(2*pi*VTable[4][7]);
                        lstBuocGiai.add("CT8: Sxq = 2π*Sq = " + String.valueOf(value));
                        break;
                }
                break;
            case 8:
                // CT9: Sxq = Co*h
                switch (element)
                {
                    case 0:
                        // h = Sxq/Co
                        value = (float)(VTable[5][8]/VTable[2][8]);
                        lstBuocGiai.add("CT9: h = Sxq/Co = " + String.valueOf(value));
                        break;
                    case 2:
                        // Co = Sxq/h
                        value = (float)(VTable[5][8]/VTable[0][8]);
                        lstBuocGiai.add("CT9: Co = Sxq/h = " + String.valueOf(value));
                        break;
                    case 5:
                        // Sxq = Co*h
                        value = (float)(VTable[2][8]*VTable[0][8]);
                        lstBuocGiai.add("CT9: Sxq = Co*h = " + String.valueOf(value));
                        break;
                }
                break;
        }
        if (value <= 0)
        {
            JOptionPane.showMessageDialog(null,"Các yếu tố nhập vào không hợp lệ!!. Vui lòng kiểm tra lại.","Báo Lỗi!",JOptionPane.OK_OPTION);
            stop = 1;
        }
        else
        {
            for (int i = 0; i < equationNumber; i++)
                if (VTable[element][i] == -1)
                {
                    VTable[element][i] = value;
                    RTable[element][i] = 1;
                }
        }
    }

    /**
     *  Change Image Cylinder Semantic
     * @return BufferedImage
     */
    public BufferedImage imageCylinderSemanticChange() {
        BufferedImage imgGoc = null;
        try {
            URL urlGoc = new URL(getClass().getResource("/CylinderSemantic/Resources/MangNguNghiaLangTruTronLienKet.png").toString());
            imgGoc = ImageIO.read(urlGoc);
        } catch (Exception e) {
            e.printStackTrace();
        }


        BufferedImage resultImg = new BufferedImage(
                imgGoc.getWidth(),
                imgGoc.getHeight(),
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = resultImg.createGraphics();
        g.drawImage(imgGoc,0,0,null);


        try {

            if(RTable[0][1] != -1){
                URL urlh = new URL(getClass().getResource("/CylinderSemantic/Resources/h.png").toString());
                final BufferedImage imgh = ImageIO.read(urlh);
                g.drawImage(imgh,0,0,null);
            }

            if(RTable[1][0] != -1){
                URL urlr = new URL(getClass().getResource("/CylinderSemantic/Resources/r.png").toString());
                final BufferedImage imgr = ImageIO.read(urlr);
                g.drawImage(imgr,0,0,null);
            }

            if(RTable[2][4] != -1){
                URL urlCo = new URL(getClass().getResource("/CylinderSemantic/Resources/Co.png").toString());
                final BufferedImage imgCo = ImageIO.read(urlCo);
                g.drawImage(imgCo,0,0,null);
            }

            if(RTable[3][0] != -1){
                URL urlSo = new URL(getClass().getResource("/CylinderSemantic/Resources/So.png").toString());
                final BufferedImage imgSo = ImageIO.read(urlSo);
                g.drawImage(imgSo,0,0,null);
            }

            if(RTable[4][2] != -1){
                URL urlSq = new URL(getClass().getResource("/CylinderSemantic/Resources/Sq.png").toString());
                final BufferedImage imgSq = ImageIO.read(urlSq);
                g.drawImage(imgSq,0,0,null);
            }

            if(RTable[5][5] != -1){
                URL urlSxq = new URL(getClass().getResource("/CylinderSemantic/Resources/Sxq.png").toString());
                final BufferedImage imgSxq = ImageIO.read(urlSxq);
                g.drawImage(imgSxq,0,0,null);
            }

            if(RTable[6][5] != -1){
                URL urlStp = new URL(getClass().getResource("/CylinderSemantic/Resources/Stp.png").toString());
                final BufferedImage imgStp = ImageIO.read(urlStp);
                g.drawImage(imgStp,0,0,null);
            }

            if(RTable[7][1] != -1){
                URL urlV = new URL(getClass().getResource("/CylinderSemantic/Resources/V.png").toString());
                final BufferedImage imgV = ImageIO.read(urlV);
                g.drawImage(imgV,0,0,null);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        g.dispose();

        return resultImg;

    }

    // Other Functions

    /**
     * Check String Is Null Or Empty
     * @param str
     * @return boolean
     */
    private boolean isNullOrEmpty(String str){
        return (str == null || str.length() == 0);
    }
}
