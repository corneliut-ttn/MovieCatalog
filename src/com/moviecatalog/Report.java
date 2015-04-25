package com.moviecatalog;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

/**
 * Created by cornelius on 3/11/15.
 */
public class Report {

    private DefaultTableModel tableModel;
    private JasperPrint jasperPrint;
    private MovieCatalog movieCatalog;

    public Report(MovieCatalog movieCatalog){
        this.movieCatalog=movieCatalog;
    }

    public void generateReport(String sortingType,String genre){
        String sortedReport;
        if(sortingType.compareTo("name")==0){
            sortedReport="name_sorted_report";
        }else if(sortingType.compareTo("rating")==0)
        {
            sortedReport="rating_sorted_report";
        }else return;
        String[] columnNames = {"MOVIE TITLE", "CATEGORIES", "LAUNCH DATE", "RATING", "IMDB ID"};
        String[][] matrixCollection=new String[movieCatalog.getColection().size()][5];
        int j=0;
        for(int i=0;i<movieCatalog.getColection().size();i++){
            if(movieCatalog.getColection().get(i).getCategories().contains(Movie.genre.valueOf(genre))){
            matrixCollection[j][0]=movieCatalog.getColection().get(i).getName();
            matrixCollection[j][1]=movieCatalog.getColection().get(i).getCategories().toString();
            matrixCollection[j][2]=movieCatalog.getColection().get(i).getLaunchDate().toString();
            matrixCollection[j][3]=String.valueOf(movieCatalog.getColection().get(i).getRating());
            matrixCollection[j][4]=String.valueOf(movieCatalog.getColection().get(i).getImdbID());
            j++;
            }
        }
        String[][] matrixCollectionFinal=new String[j][5];
        for (int i = 0; i < j; i++) {
            matrixCollectionFinal[i]=matrixCollection[i];
        }
        tableModel=new DefaultTableModel(matrixCollectionFinal,columnNames);
        try{
            JasperCompileManager.compileReportToFile(sortedReport+".jrxml");
            jasperPrint= JasperFillManager.fillReport(sortedReport+".jasper",new HashMap(), new JRTableModelDataSource(tableModel));
        }catch(JRException e){
            e.printStackTrace();
        }
        try{
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
            JasperExportManager.exportReportToPdfFile(jasperPrint, sortedReport+".pdf");
            JasperExportManager.exportReportToHtmlFile(jasperPrint, sortedReport+"html");
            JasperExportManager.exportReportToXmlFile(jasperPrint,sortedReport+".xml",false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void generateReport(String sortingType){
        String sortedReport;
        if(sortingType.compareTo("name")==0){
            sortedReport="name_sorted_report";
        }else if(sortingType.compareTo("rating")==0)
        {
            sortedReport="rating_sorted_report";
        }else return;
        String[] columnNames = {"MOVIE TITLE", "CATEGORIES", "LAUNCH DATE", "RATING", "IMDB ID"};
        String[][] matrixCollection=new String[movieCatalog.getColection().size()][5];
        int j=0;
        for(int i=0;i<movieCatalog.getColection().size();i++){
            matrixCollection[j][0]=movieCatalog.getColection().get(i).getName();
            matrixCollection[j][1]=movieCatalog.getColection().get(i).getCategories().toString();
            matrixCollection[j][2]=movieCatalog.getColection().get(i).getLaunchDate().toString();
            matrixCollection[j][3]=String.valueOf(movieCatalog.getColection().get(i).getRating());
            matrixCollection[j][4]=String.valueOf(movieCatalog.getColection().get(i).getImdbID());
            j++;
        }

        tableModel=new DefaultTableModel(matrixCollection,columnNames);
        try{
            JasperCompileManager.compileReportToFile(sortedReport+".jrxml");
            jasperPrint= JasperFillManager.fillReport(sortedReport+".jasper",new HashMap(), new JRTableModelDataSource(tableModel));
        }catch(JRException e){
            e.printStackTrace();
        }
        try{
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
            JasperExportManager.exportReportToPdfFile(jasperPrint, sortedReport+".pdf");
            JasperExportManager.exportReportToHtmlFile(jasperPrint, sortedReport+".html");
            JasperExportManager.exportReportToXmlFile(jasperPrint,sortedReport+".xml",false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }
}

