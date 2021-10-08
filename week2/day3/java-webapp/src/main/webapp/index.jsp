<%@ include file="header.jspf"%>

<h2>Search for products</h2>

<p>Enter price range in INR</p>

<form action="./get-products-by-price-range" method="get">

    <div class="row">
        <div class="col-3 form-group">
            <input placeholder="minimum price" autofocus type="number" name="min" id="min_price" class="form-control">
        </div>
    </div>
    <div class="row">
        <div class="col-3 form-group">
            <input placeholder="maximum price" type="number" name="max" id="max_price" class="form-control">
        </div>
    </div>

    <button class="btn btn-primary">Get products</button>
</form>

<%@ include file="footer.jspf"%>
