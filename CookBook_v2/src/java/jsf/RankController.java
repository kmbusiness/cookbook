package jsf;

import jpa.entities.Rank;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.RankFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("rankController")
@SessionScoped
public class RankController implements Serializable {

    private Rank current;
    private DataModel items = null;
    @EJB
    private jpa.session.RankFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public RankController() {
    }

    public Rank getSelected() {
        if (current == null) {
            current = new Rank();
            current.setRankPK(new jpa.entities.RankPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private RankFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Rank) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Rank();
        current.setRankPK(new jpa.entities.RankPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getRankPK().setRecipeName(current.getRecipe().getRecipeName());
            current.getRankPK().setUserName(current.getPerson().getUserName());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("RankCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Rank) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getRankPK().setRecipeName(current.getRecipe().getRecipeName());
            current.getRankPK().setUserName(current.getPerson().getUserName());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("RankUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Rank) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("RankDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Rank getRank(jpa.entities.RankPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Rank.class)
    public static class RankControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RankController controller = (RankController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rankController");
            return controller.getRank(getKey(value));
        }

        jpa.entities.RankPK getKey(String value) {
            jpa.entities.RankPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.RankPK();
            key.setUserName(values[0]);
            key.setRecipeName(values[1]);
            key.setRank(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(jpa.entities.RankPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUserName());
            sb.append(SEPARATOR);
            sb.append(value.getRecipeName());
            sb.append(SEPARATOR);
            sb.append(value.getRank());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Rank) {
                Rank o = (Rank) object;
                return getStringKey(o.getRankPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rank.class.getName());
            }
        }

    }

}
