app.service("DataService", function () {
  var data = [];

  var dataTongTien = "";
  return {
    getDataTongTien: function () {
      return dataTongTien;
    },
    setDataTongTien: function (newData) {
      dataTongTien = newData;
    },
    removeItemTongTien: function () {
      dataTongTien = "";
    },
    getData: function () {
      return data;
    },
    setData: function (newData) {
      data.push(newData);
    },
    removeItem: function (item) {
      var index = data.indexOf(item);
      if (index !== -1) {
        data.splice(index, 1);
      }
    },
  };
});
