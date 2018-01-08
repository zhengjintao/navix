<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.navix.core.web.constant.FarmConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<%@ taglib uri="/view/conf/farmdoc.tld" prefix="DOC"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<PF:basePath/>" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>index</title>
    <meta name="description" content=""/>
    <meta name="author" content="Christoph Oberhofer"/>

    <meta name="viewport" content="width=device-width; initial-scale=1.0"/>
    
    <jsp:include page="../atext/include-web.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="<PF:basePath/>barscan/js/css/styles.css"/>
</head>

<body>
	<jsp:include page="../commons/head.jsp"></jsp:include>
	<jsp:include page="../commons/superContent.jsp"></jsp:include>
<section id="container" class="container">
    <div class="controls">
        <fieldset class="input-group">
            <input type="file" accept="image/*" capture="camera"/>
            <button>Rerun</button>
        </fieldset>
        <fieldset class="reader-config-group">
            <label>
                <span>Barcode-Type</span>
                <select name="decoder_readers">
                    <option value="code_128">Code 128</option>
                    <option value="code_39">Code 39</option>
                    <option value="code_39_vin">Code 39 VIN</option>
                    <option value="ean" selected="selected">EAN</option>
                    <option value="ean_extended">EAN-extended</option>
                    <option value="ean_8">EAN-8</option>
                    <option value="upc">UPC</option>
                    <option value="upc_e">UPC-E</option>
                    <option value="codabar">Codabar</option>
                    <option value="i2of5">Interleaved 2 of 5</option>
                    <option value="2of5">Standard 2 of 5</option>
                    <option value="code_93">Code 93</option>
                </select>
            </label>
            <label>
                <span>Resolution (long side)</span>
                <select name="input-stream_size">
                    <option value="320">320px</option>
                    <option value="640">640px</option>
                    <option value="800">800px</option>
                    <option value="1280">1280px</option>
                    <option value="1600">1600px</option>
                    <option selected="selected" value="1920">1920px</option>
                </select>
            </label>
            <label>
                <span>Patch-Size</span>
                <select name="locator_patch-size">
                    <option value="x-small">x-small</option>
                    <option value="small">small</option>
                    <option value="medium">medium</option>
                    <option selected="selected" value="large">large</option>
                    <option value="x-large">x-large</option>
                </select>
            </label>
            <label>
                <span>Half-Sample</span>
                <input type="checkbox" name="locator_half-sample" />
            </label>
            <label>
                <span>Single Channel</span>
                <input type="checkbox" name="input-stream_single-channel" />
            </label>
            <label>
                <span>Workers</span>
                <select name="numOfWorkers">
                    <option value="0">0</option>
                    <option selected="selected" value="1">1</option>
                </select>
            </label>
        </fieldset>
    </div>
    <div id="result_strip">
        <ul class="thumbnails"></ul>
    </div>
    <div id="interactive" class="viewport"></div>
    <div id="debug" class="detection"></div>
</section>
	<jsp:include page="../commons/footServer.jsp"></jsp:include>
	<jsp:include page="../commons/foot.jsp"></jsp:include>
<script src="<PF:basePath/>barscan/js/vendor/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<PF:basePath/>barscan/dist/quagga.js" type="text/javascript"></script>
<script src="<PF:basePath/>barscan/js/file_input.js" type="text/javascript"></script>
</body>
</html>
