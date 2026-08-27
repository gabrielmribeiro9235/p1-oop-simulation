public class Principal {
    public static void main(String[] args) {
        UserAccount ana = new UserAccount("ana@email.com", "Ana");
        UserAccount bruno = new UserAccount("bruno@email.com", "Bruno");
        UserAccount carla = new UserAccount("carla@email.com", "Carla");

        ana.acceptFollower(bruno);
        ana.acceptFollower(carla);

        ana.publish("Acredite no seu potencial!");
        ana.publish("Cada dia é uma nova chance.");

        System.out.println("== Timeline do Bruno ==");
        System.out.println(bruno.showTimeline());

        bruno.clapPost(0);
        carla.booPost(1);

        System.out.println("== Timeline do Bruno após reações ==");
        System.out.println(bruno.showTimeline());

        System.out.println("== Posts da Ana ==");
        System.out.println(ana.showMyPosts());

        System.out.println("== Seguidores da Ana ==");
        System.out.println(ana.showMyFriends());

        ana.blockFollower(carla);
        ana.publish("Essa mensagem só chega para o Bruno.");

        System.out.println("== Timeline da Carla (não deve ter a nova postagem) ==");
        System.out.println(carla.showTimeline());

        System.out.println("== Timeline do Bruno (deve ter a nova postagem) ==");
        System.out.println(bruno.showTimeline());

        boolean removed = ana.delete(0);
        System.out.println("Post removido com sucesso? " + removed);
        System.out.println("== Posts da Ana após remoção ==");
        System.out.println(ana.showMyPosts());

        System.out.println("== Timeline do Bruno (deve ter o post que Ana excluiu) ==");
        System.out.println(bruno.showTimeline());

        System.out.println("== Teste do buffer circular da timeline do Bruno (>10 posts) ==");
        for (int i = 1; i <= 12; i++) {
            ana.publish("Mensagem número " + i);
        }
        System.out.println(bruno.showTimeline());
    }
}