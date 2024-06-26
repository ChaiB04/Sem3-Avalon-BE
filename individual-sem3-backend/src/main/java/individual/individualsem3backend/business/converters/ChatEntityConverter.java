package individual.individualsem3backend.business.converters;

import individual.individualsem3backend.domain.Chat;
import individual.individualsem3backend.domain.ChatMessage;
import individual.individualsem3backend.persistence.entity.ChatEntity;
import individual.individualsem3backend.persistence.entity.MessageEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatEntityConverter {

    private ChatEntityConverter(){}

    public static ChatMessage chatMessageEntityToChatMessage(MessageEntity dto){
        return ChatMessage.builder()
                .id(dto.getId())
                .chat_id(dto.getChat_id())
                .from(dto.getSender())
                .text(dto.getMessage())
                .date(dto.getDate())
                .seen(dto.getSeen())
                .build();
    }

    public static MessageEntity chatMessageToChatMessageEntity(ChatMessage chatMessage){
        return MessageEntity.builder()
                .id(chatMessage.getId())
                .chat_id(chatMessage.getChat_id())
                .sender(chatMessage.getFrom())
                .message(chatMessage.getText())
                .date(chatMessage.getDate())
                .seen(chatMessage.getSeen())
                .build();
    }


    public static Chat chatEntityToChat(ChatEntity dto){
        return Chat.builder()
                .id(dto.getId())
                .user_1(dto.getUser1())
                .user_2(dto.getUser2())
                .chatMessages(dto.getMessages().stream().map(ChatEntityConverter::chatMessageEntityToChatMessage).toList())
                .build();
    }

    public static ChatEntity chatConvertedToChatEntity(Chat chatMessage){
        return ChatEntity.builder().id(chatMessage.getId())
                .user1(chatMessage.getUser_1())
                .user2(chatMessage.getUser_2())
                .messages(chatMessage.getChatMessages().stream().map(ChatEntityConverter::chatMessageToChatMessageEntity).toList())
                .build();
    }




}
