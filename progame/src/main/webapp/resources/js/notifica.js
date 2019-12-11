new Vue({
    el:'#notifica',
    vuetify: new Vuetify(),
  data () {
    return {
      search: '',
      headers: [
        {
            sortable: false,
            key: "idDesafio",
            label: "Desafio"
        },
        {
            sortable: false,
            key: "tituloDesafio",
            label: "Título"
        },
        {
            sortable: false,
            key: "desafio",
            label: "Desafio"
        },
        {
            sortable: false,
            key: "respostaDesafiado",
            label: "Sua resposta"
        },
        {
            sortable: false,
            key: "resultado",
            label: "Resultado"
        }
     ],
      totalNotificacao: 0,
      desafios: []
    }
  },
    created: function(){
        const vm = this;
        var count = 0;
        axios.get('../rs/user/getUser').then(function(response){
            axios.get('../rs/user/todosDesafios/'+response.data["matricula"]).then(function(response){
                while(response.data[count]){
                    if(response.data[count]['resultado'] == '' || response.data[count]['resultadp'] == null){
                        response.data[count]['resultado'] = 'Aguardando correção';
                    }

                    if(response.data[count]['respostaDesafiado'] == '' || response.data[count]['respostaDesafiado'] == null){
                        response.data[count]['respostaDesafiado'] = 'Sem resposta';
                    }
                    response.data[count]["idDesafio"] = count+1;
                    vm.desafios.push(response.data[count]);
                    count+=1;
                }
                vm.totalNotificacao = count;
            });
        });
    }
});
