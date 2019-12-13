new Vue({
  el:'#notifica',
  data: {
    totalvs: 0,
    totalNotificacao: 0
  },
  created: function(){
    const vm = this;
    var count = 0;
    axios.get('../rs/user/getUser').then(function(response){
      axios.get('../rs/desafio/todosDesafios/'+response.data["matricula"]).then(function(response){
          while(response.data[count]){
              count+=1;
          }
          vm.totalvs = count;
          vm.totalNotificacao = 1;
      });
  });
  }




});