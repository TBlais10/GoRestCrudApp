package careerdevs.springboot.GoRestCrudApp.Model;

public class GoRestMeta {
    public Paginiation pagination;

    public Paginiation getPagination() {
        return pagination;
    }

    public void setPagination(Paginiation pagination) {
        this.pagination = pagination;
    }

    public class Paginiation{
        private String total;
        private int pages;
        private String page;
        private String limit;
        private Object links;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public Object getLinks() {
            return links;
        }

        public void setLinks(Object links) {
            this.links = links;
        }
    }

}
