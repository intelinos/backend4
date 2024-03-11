package org.example.back4.dbUtils;

import org.example.back4.beans.AreaCheckerBean;

import java.sql.SQLException;
import java.util.Collection;

public interface CheckAreaDAO {
    void addNewResult(AreaCheckerBean result) throws SQLException;
    void updateResult(Long bus_id, AreaCheckerBean result) throws SQLException;
    AreaCheckerBean getResultById(Long result_id) throws SQLException;
    Collection<AreaCheckerBean> getAllResults() throws SQLException;
    Collection<AreaCheckerBean> getUserResults(int owner_id) throws SQLException;
    Collection<AreaCheckerBean> getSortedResults(String field, String operator, double value) throws SQLException;
    void deleteResult(AreaCheckerBean result) throws SQLException;
    void clearResults() throws SQLException;
    void clearUserResults(int owner_id) throws SQLException;
}