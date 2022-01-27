document.write(
    "<section>"+
    "        <nav className=\"navbar navbar-dark\" style=\"background-color: #0F222D;\">\n" +
    "    \n" +
    "        <div className=\"container-fluid justify-content px-5\">\n" +
    "            <a className=\"navbar-brand\">\n" +
    "                <img src=\"/images/Logo.png\" alt=\"\" width=\"180\" height=\"42\">\n" +
    "            </a>\n" +
    "    \n" +
    "            <form className=\"d-flex\" name=\"f\" th:action=\"@{/logout}\" method=\"post\">\n" +
    "                <a href=\"/vault/showdata\" className=\"btn btn-outline-light btn-lg m-1\" role=\"button\">Vault</a>\n" +
    "                <a href=\"/vault/accountview\" className=\"btn btn-outline-light btn-lg m-1\" role=\"button\">Account</a>\n" +
    "                <button type=\"submit\" className=\"btn btn-outline-light btn-lg m-1\">Logout</button>\n" +
    "            </form>\n" +
    "        </div>\n" +
    "    </nav>" +
    "</section>");

