$(document).ready( function () {
    var table = $('#orderTable').DataTable({
        "sAjaxSource": "/orders",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "domainId"},
            { "mData": "domainName" },
            { "mData": "status" },
            { "mData": "expires" },
            { "mData": "renewAuto" },
            { "mData": "createdAt" },
            { "mData": "domainProvider" }
        ]
    })
});