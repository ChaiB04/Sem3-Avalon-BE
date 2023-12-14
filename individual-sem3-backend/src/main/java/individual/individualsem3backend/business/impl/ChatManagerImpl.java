package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ChatManager;
import individual.individualsem3backend.business.converters.ChatEntityConverter;
import individual.individualsem3backend.business.exception.WebSocketException;
import individual.individualsem3backend.domain.*;
import individual.individualsem3backend.persistence.*;
import individual.individualsem3backend.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ChatManagerImpl implements ChatManager {
    private final SimpMessagingTemplate messagingTemplate;
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public ChatMessage sendMessage(ChatMessage message){
        try{
            messagingTemplate.convertAndSend("/topic/publicmessages", message);

            message.setSeen(false);

            if(message.getChat_id() != null){
                if(chatRepository.existsById(message.getChat_id())){

                    MessageEntity saveEntity = ChatEntityConverter.chatMessageToChatMessageEntity(message);
                    MessageEntity entity = messageRepository.save(saveEntity);
                    if(entity != null){

                        return ChatEntityConverter.chatMessageEntityToChatMessage(entity);
                    }
                    else throw new WebSocketException("Error creating message.");
                }
                else{
                    throw new WebSocketException("Chat not found.");
                }
            }
            else{
                Chat newChat = Chat.builder()
                        .user_1(message.getFrom())
                        .user_2(message.getTo())
                        .chatMessages(new ArrayList<>())
                        .build();


                ChatEntity id = chatRepository.saveAndFlush(ChatEntityConverter.chatConvertedToChatEntity(newChat));

                message.setChat_id(id.getId());

                MessageEntity entity = messageRepository.save(ChatEntityConverter.chatMessageToChatMessageEntity(message));

                if(entity != null){
                     return ChatEntityConverter.chatMessageEntityToChatMessage(entity);
                }
                else throw new WebSocketException("Error creating message.");

            }
//            throw new WebSocketException("Error with making chat or getting message");
        }
        catch(Exception ex){
            throw new WebSocketException(ex.getMessage());
        }
    }

    public Chat getChat(Integer id){
        try{
            Optional<ChatEntity> chat = chatRepository.findById(id);

            if(chat.isPresent()){
                return ChatEntityConverter.chatEntityToChat(chat.get());
            }
            else{
                throw new WebSocketException("Cannot find chat");
            }
        }
        catch(Exception ex){
            throw new WebSocketException(ex.getMessage());
        }
    }

    public List<Chat> getAllChatsCustomerService(){
        try{
            List<ChatEntity> chat1 = chatRepository.findAllByUser1(2);
            List<ChatEntity> chat2 = chatRepository.findAllByUser2(2);

            List<ChatEntity> combinedList = new ArrayList<>();
            combinedList.addAll(chat1);
            combinedList.addAll(chat2);

            return combinedList.stream().map(ChatEntityConverter::chatEntityToChat).toList();
        }
        catch(Exception ex){
            throw new WebSocketException("Something went wrong with getting all the chat's for the customer service.");
        }
    }

    public Chat getChatOfCustomer(Integer id){
        try{
           ChatEntity entity1 = chatRepository.findByUser1(id);

           if(entity1 != null || entity1.getId() != null){
               return ChatEntityConverter.chatEntityToChat(entity1);
           }
           else{
               ChatEntity entity2 = chatRepository.findByUser2(id);
               return ChatEntityConverter.chatEntityToChat(entity2);

           }
        }
        catch(Exception ex){
            throw new WebSocketException("Something went wrong with getting the chat of the customer");
        }
    }
}
