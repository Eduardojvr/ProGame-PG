new Vue({
    el:'#notifica',
    vuetify: new Vuetify(),
  data () {
    return {
      search: '',
      headers: [
            {
              align: 'left',
              sortable: false,
            },
            { value: 'idDesafio', text: 'Prioridade Desafio' },
            { value: 'tituloDesafio', text: 'Título' },
            { value: 'desafio', text: 'Desafio' },
            { value: 'respostaDesafiado', text: 'Sua resposta' },
            { value: 'resultado', text: 'Resultado' } 
       ],
      desafios:[
          {
              desafio: '',
              idDesafio: '',
              matriculaDesafiado: '',
              matriculaDesafiante: '',
              respostaDesafiado: '',
              resultado: '',
              tituloDesafio:''
          }
      ],
      totalNotificacao: 0

    }
  },
    created: function(){
        const vm = this;
        var count = 0;
        axios.get('../rs/user/getUser').then(function(response){
            axios.get('../rs/user/todosDesafios/'+response.data["matricula"]).then(function(response){
                while(response.data[count]){
                    if(response.data[count]['resultado'] == '' || response.data[count]['resultado'] == null){
                        response.data[count]['resultado'] = 'Aguardando correção';
                    }

                    if(response.data[count]['respostaDesafiado'] == '' || response.data[count]['respostaDesafiado'] == null){
                        response.data[count]['respostaDesafiado'] = 'Sem resposta';
                    }
                    response.data[count]["idDesafio"] = count+1;
                    // vm.desafios.push(response.data[count]);
                    count+=1;
                }
                vm.totalNotificacao = count;
                vm.desafios = response.data;
            });
        });
    }
});

