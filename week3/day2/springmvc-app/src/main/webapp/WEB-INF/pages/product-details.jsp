<%@ include file="./header.jspf" %>

<div class="row">
    <div class="col">
        <img
            class="img-thumbnail"
            src="${prd.picture}"
            alt="${prd.description}"
        />
    </div>
    <div class="col">
        <h3>${prd.name}</h3>

        <table class="table">
            <tr>
                <td>Description</td>
                <td>${prd.description}</td>
            </tr>
            <tr>
                <td>Quantity per unit</td>
                <td>${prd.quantityPerUnit}</td>
            </tr>
            <tr>
                <td>Unit price</td>
                <td>${prd.unitPrice}</td>
            </tr>
            <tr>
                <td>Brand</td>
                <td>${prd.brand}</td>
            </tr>
            <tr>
                <td>Category</td>
                <td>${prd.category}</td>
            </tr>
            <tr>
                <td>Discount</td>
                <td>${prd.discount}%</td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="./footer.jspf" %>
