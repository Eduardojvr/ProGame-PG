// let q = new Vue({
//     el : '#quest',
//     data : {
//         listaQuestao : []
//     },
//     created :function(){
//         const vm = this;
//         vm.getQuestao();
//     },
//     methods : {
//         getQuestao : function(){
//             const vm = this;
//             axios.get('../rs/questao/getQuestao/'+sessionStorage.getItem('level')).then(function(response) {
//                 console.log(response.data);
//                 vm.listaQuestao = response.data;
//             }).catch(function (error){
//                 alert("Erro!");
//             });
//         }
//     }

// });