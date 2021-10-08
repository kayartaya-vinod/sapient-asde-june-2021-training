<%@ include file="./header.jspf" %>
<table class="table table-bordered">
    <caption>
        ${title}
    </caption>
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Quantity per unit</th>
            <th scope="col">Unit price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${products}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>
                    <a href="./product-details?id=${p.id}"> ${p.name} </a>
                </td>
                <td>${p.description}</td>
                <td>${p.quantityPerUnit}</td>
                <td>${p.unitPrice}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="./footer.jspf" %>
